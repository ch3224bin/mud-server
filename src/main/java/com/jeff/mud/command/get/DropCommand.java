package com.jeff.mud.command.get;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.service.ItemBrokerService;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;

@Component
public class DropCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final ItemBrokerService itemBrokerService;
	
	public DropCommand(CustomMessagingTemplate customMessagingTemplate, ItemBrokerService itemBrokerService) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.itemBrokerService = itemBrokerService;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.NORMAL, CharactorState.COMBAT);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.drop;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		if (!input.hasTarget()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("무엇을 버릴까요?"));
			return;
		}
		
		Optional<Item> item = input.getPlayer().getCharactorBag().getItem(input.getTarget());
		if (item.isEmpty()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("당신의 소지품에는 %s이(가) 없습니다.", input.getTarget()));
			return;
		}
		
		itemBrokerService.moveToRoom(item.get(), input.getPlayer());
		customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("당신은 %s을(를) 버렸습니다.", item.get().getName()));
		customMessagingTemplate.sendToRoomWithOutMe(input, Template.defaultMessage, String.format("%s이(가) %s을(를) 버렸습니다.", input.getPlayer().getName(), item.get().getName()));
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
