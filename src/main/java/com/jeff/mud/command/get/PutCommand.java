package com.jeff.mud.command.get;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.get.finder.ItemFinder;
import com.jeff.mud.domain.item.domain.Container;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.service.ItemBrokerService;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;
import com.jeff.mud.template.TemplateDc;

@Component
public class PutCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final ItemFinder itemFinder;
	private final ItemBrokerService itemBrokerService;
	
	public PutCommand(CustomMessagingTemplate customMessagingTemplate, ItemFinder itemFinder, ItemBrokerService itemBrokerService) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.itemFinder = itemFinder;
		this.itemBrokerService = itemBrokerService;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.NORMAL, CharactorState.COMBAT);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.put;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		if (!input.hasTarget()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("어디에 넣을까요?"));
			return;
		}
		
		if (!input.hasSecondTarget()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("무엇을 넣을까요?"));
			return;
		}
		
		Item container = itemFinder.findTarget(input);
		if (container == null) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("%s이(가) 없습니다.", input.getTarget()));
			return;
		}
		if (!(container instanceof Container)) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("%s에는 넣을 수 없습니다.", container.getName()));
			return;
		}
		
		if (((Container) container).isLocked()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("%s이(가) 잠겨있습니다.", container.getName()));
			return;
		}
		
		Optional<Item> item = input.getPlayer().getCharactorBag().getItem(input.getSecondTarget());
		if (item.isEmpty()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("당신의 소지품에는 %s이(가) 없습니다.", input.getTarget()));
			return;
		}
		
		itemBrokerService.moveToContainer(input.getPlayer(), (Container) container, item.get());
		
		TemplateDc templateDc = TemplateDc.builder().player(input.getPlayer().getName()).container(container.getName())
				.item(item.get().getName()).build();
		customMessagingTemplate.sendToYou(input.getUsername(), Template.putSendMe, templateDc);
		customMessagingTemplate.sendToRoomWithOutMe(input, Template.putSendRoom, templateDc);
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
