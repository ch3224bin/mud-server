package com.jeff.mud.command.who.command;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.who.dto.WhoDc;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.global.command.Command;
import com.jeff.mud.global.command.CommandHandler;
import com.jeff.mud.global.command.Input;
import com.jeff.mud.global.message.CustomMessagingTemplate;

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
	
	private final static CommandConstants WHO = CommandConstants.WHO;
	private final static String TEMPLATE_LOCATION = WHO.getLocation();
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final CurrentUserManager currentUserManager;
	
	public WhoCommand(CommandHandler commandHandler, CustomMessagingTemplate customMessagingTemplate, CurrentUserManager currentUserManager) {
		commandHandler.addCommand(this);
		this.customMessagingTemplate = customMessagingTemplate;
		this.currentUserManager = currentUserManager;
	}

	@Override
	public boolean execute(Input input) {
		// 내 껀지 판단
		if (!WHO.matched(input.getCommand())) {
			return false;
		}
		// 처리
		customMessagingTemplate.convertAndSendToYou(TEMPLATE_LOCATION, new WhoDc(currentUserManager.getCurrentPlayers()));
		return true;
	}

}
