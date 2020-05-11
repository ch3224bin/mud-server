package com.jeff.mud.command.get;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.get.finder.GetFinder;
import com.jeff.mud.command.get.model.Getable;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.template.Template;

@Component
public class GetCommand extends Command {
	
	private final GetFinder getFinder = new GetFinder();
	private final CustomMessagingTemplate customMessagingTemplate;
	
	public GetCommand(CustomMessagingTemplate customMessagingTemplate) {
		this.customMessagingTemplate = customMessagingTemplate;
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
		// TODO 가질수 있는 물건을 찾아야 함.
		// 1. 방 안의 물건. 2. 방안의 물건 중 컨테이너의 속에 들어있는 것.
		if (!input.hasTarget()) {
			return;
		}
		
		Getable target = getFinder.findTarget(input);
		if (target == null) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s은(는) 가질 수 없습니다.", input.getTarget()));
			return;
		}
		
		// TODO 동시에 주웠을때
		target.moveTo(input.getPlayer());
		customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("당신은 %s을(를) 주웠습니다.", input.getTarget()));
		customMessagingTemplate.convertAndSendToRoomWithOutMe(input, Template.defaultMessage, String.format("%s이(가) %s을(를) 주웠습니다.", input.getPlayer().getName(), input.getTarget()));
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
