package com.jeff.mud.welcome.listener;

import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.event.StatusChangeEvent;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.event.GameStartEvent;
import com.jeff.mud.template.Template;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

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
	private final ApplicationEventPublisher applicationEventPublisher;

	public Receptionist(
			CustomMessagingTemplate customMessagingTemplate,
			PlayerRepository playerRepository,
			ApplicationEventPublisher applicationEventPublisher) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.playerRepository = playerRepository;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Transactional
	@EventListener
	public void handleWebSocketConnectListener(SessionSubscribeEvent event) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
		String destination = accessor.getDestination();
		Player player = playerRepository.findByUsername(accessor.getUser().getName()).get();
		if ("/user/history".equals(destination)) {
			// 1. 웰컴 메세지
			customMessagingTemplate.sendToYou(accessor.getUser().getName(), Template.welcome, null);
			// 2. 공지사항등

			// 3. 첫번째로 할 행동을 취한다.
			applicationEventPublisher.publishEvent(new GameStartEvent(player));
		}
		if ("/user/history/status".equals(destination)) {
			if (player.getStatus() != null)
				applicationEventPublisher.publishEvent(new StatusChangeEvent(player.getStatus())); // 간략한 상태정보를 출력하기 위해 event trigger
		}
	}
}
