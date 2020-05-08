package com.jeff.mud.state.firststep;

import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.player.dto.PlayerDc;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.state.StateHandler;

@Component
public class FirstStepStateHandler implements StateHandler {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final CurrentUserManager currentUserManager;
	
	public FirstStepStateHandler(CustomMessagingTemplate customMessagingTemplate, CurrentUserManager currentUserManager) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.currentUserManager = currentUserManager;
	}

	@Override
	public boolean handle(Command command, CommandDataCarrier dc) {
		PlayerDc player = currentUserManager.getPlayer(dc.getUsername());
		String flashState = Strings.nullToEmpty(player.getFlashState());
		String message = "";
		FirstStep nextstep = null;
		if (dc.getMsg().isBlank() ||
				!flashState.startsWith("cc")) {
			nextstep = FirstStep.cc1;
			message = "처음오셨군요.<br>이름을 입력해주세요.";
		} else if (FirstStep.contains(flashState)) {
			FirstStep currstep = FirstStep.valueOf(flashState);
			switch (currstep) {
				case cc1 :
					if (Strings.isNullOrEmpty(dc.getMsg())) {
						message = "이름을 입력해주세요.";
						break;
					}
					nextstep = FirstStep.cc2;
					message = String.format("$s이(가) 맞습니까?", dc.getMsg());
					break;
				case cc2 :
					
					break;
				default :
					break;
			}
		}
		if (nextstep != null) {
			player.setFlashState(nextstep.toString());
		}
		customMessagingTemplate.convertAndSendToYou(dc.getUsername(), "default_message", message);
		return true;
	}

	@Override
	public PlayerState state() {
		return PlayerState.character_create1;
	}

}
