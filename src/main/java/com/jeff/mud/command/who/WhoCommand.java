package com.jeff.mud.command.who;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.who.dto.WhoDc;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.template.Template;

/**
 * "누구" 명령어 처리기
 * 
 * <p>현재 접속자를 보여준다.</p>
 * 
 * @author ChangHo Vin
 *
 */
@Component
public class WhoCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final CurrentUserManager currentUserManager;
	
	public WhoCommand(CustomMessagingTemplate customMessagingTemplate, CurrentUserManager currentUserManager) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.currentUserManager = currentUserManager;
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.who;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.who, new WhoDc(currentUserManager.getCurrentPlayers()));
	}

	@Override
	protected List<PlayerState> allowStates() {
		return Arrays.asList(PlayerState.normal);
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
