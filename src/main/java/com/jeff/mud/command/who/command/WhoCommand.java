package com.jeff.mud.command.who.command;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.who.dto.WhoDc;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.global.command.Command;
import com.jeff.mud.global.command.CommandDataCarrier;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;

/**
 * "누구" 명령어 처리기
 * 
 * <p>현재 접속자를 보여준다.</p>
 * 
 * @author ChangHo Vin
 *
 */
@Component
public class WhoCommand implements Command {
	
	private final static String TEMPLATE_LOCATION = "who";
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final CurrentUserManager currentUserManager;
	
	public WhoCommand(CustomMessagingTemplate customMessagingTemplate, CurrentUserManager currentUserManager) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.currentUserManager = currentUserManager;
	}

	@Override
	public CommandConstants commandConstants() {
		return CommandConstants.who;
	}

	@Override
	public void handle(CommandDataCarrier input) {
		customMessagingTemplate.convertAndSendToYou(input.getUsername(), TEMPLATE_LOCATION, new WhoDc(currentUserManager.getCurrentPlayers()));
	}

	@Override
	public List<PlayerState> allowStates() {
		return Arrays.asList(PlayerState.normal);
	}

	@Override
	public void handleDenyState(PlayerState state) {
		if (PlayerState.combat == state) {
			// 전투중에는 이동할 수 없습니다.
		}
	}

}
