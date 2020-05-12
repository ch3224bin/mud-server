package com.jeff.mud.command.get;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.get.finder.GetFinder;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.service.ItemBrokerService;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.template.Template;

@Component
public class GetCommand extends Command {
	
	private final GetFinder getFinder;
	private final CustomMessagingTemplate customMessagingTemplate;
	private final ItemBrokerService itemBrokerService;
	
	public GetCommand(CustomMessagingTemplate customMessagingTemplate, GetFinder getFinder, ItemBrokerService itemBrokerService) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.getFinder = getFinder;
		this.itemBrokerService = itemBrokerService;
	}

	@Override
	protected List<PlayerState> allowStates() {
		return Arrays.asList(PlayerState.normal, PlayerState.combat);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.get;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		if (!input.hasTarget()) {
			return;
		}
		
		Item item = getFinder.findTarget(input);
		if (item == null) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s이(가) 없습니다.", input.getTarget()));
			return;
		}
		
		if (!item.isGetable()) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s은(는) 가질 수 없습니다.", item.getName()));
			return;
		}
		
		// TODO 동시에 주웠을때 처리
		itemBrokerService.moveToPlayer(item, input.getPlayer());
		
		customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("당신은 %s을(를) 주웠습니다.", item.getName()));
		customMessagingTemplate.convertAndSendToRoomWithOutMe(input, Template.defaultMessage, String.format("%s이(가) %s을(를) 주웠습니다.", input.getPlayer().getName(), item.getName()));
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
