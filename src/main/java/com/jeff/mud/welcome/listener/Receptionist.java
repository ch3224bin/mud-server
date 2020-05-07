package com.jeff.mud.welcome.listener;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.jeff.mud.domain.player.dao.PlayerRepository;
import com.jeff.mud.domain.player.domain.Player;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;

/**
 * 사용자 들어왔을때, 떠났을때 처리.
 * 
 * <p>
 * {@code SessionSubscribeEvent} 구독이벤트로 처리한다.
 * </p>
 * 
 * @author ChangHo Vin
 *
 */
@Component
public class Receptionist {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final PlayerRepository playerRepository;
	
	public Receptionist(CustomMessagingTemplate customMessagingTemplate, PlayerRepository playerRepository) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.playerRepository = playerRepository;
	}
	
	@EventListener
	public void handleWebSocketConnectListener(SessionSubscribeEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String destination = accessor.getDestination();
		if ("/user/history".equals(destination)) {
			// 1. 웰컴 메세지
			customMessagingTemplate.convertAndSendToYou(accessor.getUser().getName(), "welcome", null);
			// 2. 공지사항등
			
			// 3. 첫번째로 할 행동을 취한다.
			// 처음 왔으면 이름 정하는 등의 캐릭터 생성
			// 보통은 현재 위치를 보여줌
			playerRepository.findByUsername(accessor.getUser().getName())
				.orElse(Player.builder()
							.state(PlayerState.character_create1)
							.build());
			
			// TODO first action
		}
	}
}
