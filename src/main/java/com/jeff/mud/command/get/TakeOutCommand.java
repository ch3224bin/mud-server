package com.jeff.mud.command.get;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.get.finder.ContainerFinder;
import com.jeff.mud.domain.item.domain.Container;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.service.ContainerItemBrokerService;
import com.jeff.mud.domain.item.service.ItemBrokerService;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.template.Template;
import com.jeff.mud.template.TemplateDc;

@Component
public class TakeOutCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final ContainerFinder containerFinder;
	private final ContainerItemBrokerService containerItemBrokerService;
	private final ItemBrokerService itemBrokerService;
	
	public TakeOutCommand(CustomMessagingTemplate customMessagingTemplate,
			ContainerFinder containerFinder,
			ContainerItemBrokerService containerItemBrokerService,
			ItemBrokerService itemBrokerService) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.containerFinder = containerFinder;
		this.containerItemBrokerService = containerItemBrokerService;
		this.itemBrokerService = itemBrokerService;
	}

	@Override
	protected List<PlayerState> allowStates() {
		return Arrays.asList(PlayerState.normal, PlayerState.combat);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.takeout;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		if (!input.hasTarget()) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("어디에서 꺼낼까요?"));
			return;
		}
		
		if (!input.hasSecondTarget()) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("무엇을 꺼낼까요?"));
			return;
		}
		
		Container container = containerFinder.findTarget(input);
		if (container == null) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s이(가) 없습니다.", input.getTarget()));
			return;
		}
		
		if (container.isLocked()) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s이(가) 잠겨있습니다.", container.getName()));
			return;
		}
		
		Optional<Item> item = containerItemBrokerService.getItemByContainer(container, input.getSecondTarget());
		if (item.isEmpty()) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s이(가) 없습니다.", input.getTarget()));
			return;
		}
		
		if (!item.get().isGetable()) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s은(는) 가질 수 없습니다.", item.get().getName()));
			return;
		}
		
		// TODO 동시에 꺼냈을때 처리
		itemBrokerService.moveToPlayer(item.get(), input.getPlayer());
		
		TemplateDc templateDc = TemplateDc.builder().player(input.getPlayer().getName()).container(container.getName())
				.item(item.get().getName()).build();
		customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.takeOutSendMe, templateDc);
		customMessagingTemplate.convertAndSendToRoomWithOutMe(input, Template.takeOutSendRoom, templateDc);
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
