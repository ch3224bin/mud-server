package com.jeff.mud.command.get;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.get.finder.ItemFinder;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.service.ItemBrokerService;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;
import com.jeff.mud.template.TemplateDc;

@Component
public class GetCommand extends Command {
	
	private final ItemFinder itemFinder;
	private final CustomMessagingTemplate customMessagingTemplate;
	private final ItemBrokerService itemBrokerService;
	
	public GetCommand(CustomMessagingTemplate customMessagingTemplate, ItemFinder itemFinder, ItemBrokerService itemBrokerService) {
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
		return CommandConstants.get;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		if (!input.hasTarget()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("무엇을 가질까요?"));
			return;
		}
		
		Item item = itemFinder.findTarget(input);
		if (item == null) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("%s이(가) 없습니다.", input.getTarget()));
			return;
		}
		
		if (!item.isGetable()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("%s은(는) 가질 수 없습니다.", item.getName()));
			return;
		}
		
		// TODO 동시에 주웠을때 처리
		itemBrokerService.moveToPlayer(item, input.getPlayer());
		
		TemplateDc templateDc = TemplateDc.builder().player(input.getPlayer().getName())
				.item(item.getName()).build();
		customMessagingTemplate.sendToYou(input.getUsername(), Template.getSendMe, templateDc);
		customMessagingTemplate.sendToRoomWithOutMe(input, Template.getSendRoom, templateDc);
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
