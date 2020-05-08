package com.jeff.mud.welcome.listener;

import java.security.Principal;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.CommandHandler;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.player.dao.PlayerRepository;
import com.jeff.mud.domain.player.domain.Player;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.state.PlayerStateHandlerManager;

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
	private final PlayerStateHandlerManager playerStateHandlerManager;
	private final CommandHandler commandHandler;
	
	public Receptionist(
			CustomMessagingTemplate customMessagingTemplate,
			PlayerRepository playerRepository,
			PlayerStateHandlerManager playerStateHandlerManager,
			CommandHandler commandHandler) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.playerRepository = playerRepository;
		this.playerStateHandlerManager = playerStateHandlerManager;
		this.commandHandler = commandHandler;
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
			Player player = playerRepository.findByUsername(accessor.getUser().getName())
				.orElse(Player.builder()
							.state(PlayerState.character_create1)
							.build());
			
			welcome(accessor.getUser(), player, player.getState());
		}
	}
	
	private void welcome(Principal principal, Player player, PlayerState state) {
		CommandDataCarrier dc = new CommandDataCarrier(principal, player, "봐");
		if (state == PlayerState.normal) {
			playerStateHandlerManager.handle(state, commandHandler.getCommand(CommandConstants.see), dc);
			return;
		}
		
		playerStateHandlerManager.handle(state, commandHandler.getCommand(CommandConstants.noop), dc);
	}
}
