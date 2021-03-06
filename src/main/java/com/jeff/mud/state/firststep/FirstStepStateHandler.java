package com.jeff.mud.state.firststep;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;
import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.state.StateHandler;
import com.jeff.mud.state.event.GameStartEvent;
import com.jeff.mud.template.Template;

@Component
public class FirstStepStateHandler implements StateHandler {

	private final CustomMessagingTemplate customMessagingTemplate;
	private final CurrentUserManager currentUserManager;
	private final PlayerRepository playerRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	public FirstStepStateHandler(
			CustomMessagingTemplate customMessagingTemplate,
			CurrentUserManager currentUserManager,
			PlayerRepository playerRepository,
			ApplicationEventPublisher applicationEventPublisher) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.currentUserManager = currentUserManager;
		this.playerRepository = playerRepository;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public boolean handle(Command command, CommandDataCarrier dc) {
		CharactorDc player = currentUserManager.getPlayer(dc.getUsername()); // FlashState를 이용하기 위함
		FirstStep currstep = getFlashState(player);
		String message = "";
		FirstStep nextstep = null;

		switch (currstep) {
			case cc0 :
				nextstep = FirstStep.cc1;
				message = "처음오셨군요.<br>이름을 입력해주세요.";
				break;
			case cc1 :
				if (Strings.isNullOrEmpty(dc.getMsg())) {
					message = "이름을 입력해주세요.";
					break;
				}
				if (playerRepository.findByName(dc.getMsg()).isPresent()) {
					message = String.format("%s은(는) 사용중인 이름입니다.<br>이름을 입력해주세요.", dc.getMsg());
					break;
				}
				nextstep = FirstStep.cc2;
				message = String.format("%s이(가) 맞습니까? (예/아니오)", dc.getMsg());
				player.setTemp1(dc.getMsg());
				break;
			case cc2 :
				if ("예".equals(dc.getMsg())) {
					nextstep = FirstStep.cc3;
					dc.getPlayer().setName(player.getTemp1());
					dc.getPlayer().changeState(CharactorState.NORMAL);
					// TODO 기타 설정 추가
					message = String.format("%s님 환영합니다.", player.getTemp1());
					applicationEventPublisher.publishEvent(new GameStartEvent(dc.getPlayer()));
				} else if ("아니오".equals(dc.getMsg())) {
					nextstep = FirstStep.cc1;
					message = "이름을 입력해주세요.";
				} else {
					message = "예 또는 아니오로 대답해주세요.";
				}
				break;
			default :
				break;
		}

		setFlashStateAndSendMessage(dc.getUsername(), player, message, nextstep);
		return true;
	}

	private FirstStep getFlashState(CharactorDc player) {
		String flashState = Strings.nullToEmpty(player.getFlashState());
		if (FirstStep.contains(flashState)) {
			return FirstStep.valueOf(flashState);
		}
		return FirstStep.cc0;
	}

	private void setFlashStateAndSendMessage(String username, CharactorDc player, String message,
			FirstStep nextstep) {
		if (nextstep != null) {
			player.setFlashState(nextstep.toString());
		}
		customMessagingTemplate.sendToYou(username, Template.defaultMessage, message);
	}

	@Override
	public CharactorState state() {
		return CharactorState.CHARACTER_CREATE1;
	}

}
