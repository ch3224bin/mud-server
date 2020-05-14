package com.jeff.mud.command.who.listener;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.global.account.dao.AccountRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 현재 접속자 관리
 * 
 * WebSocketConnectionEventListener에 등록하여 현재 접속자를 Map으로 관리한다. 
 * 
 * @author ch3224bin
 */
@Slf4j
@Transactional
@Component
public class CurrentUserManager {
	
	private final Map<String, CharactorDc> currentUserStore = new ConcurrentHashMap<>();
	
	private final AccountRepository accountRepository;
	private final PlayerRepository playerRepository;
	
	public CurrentUserManager(
			AccountRepository accountRepository,
			PlayerRepository playerRepository) {
		this.accountRepository = accountRepository;
		this.playerRepository = playerRepository;
	}
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		String username = event.getUser().getName();
		CharactorDc player = accountRepository.findByUsername(username)
				.map(account -> playerRepository.findByAccount(account).get())
				.map(CharactorDc::new)
				.get();
			
		currentUserStore.put(username, player);
		log.info("Connected : " + username);
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		String username = event.getUser().getName();
		currentUserStore.remove(username);
		log.info("Disconnected : " + username);
	}
	
	public Collection<CharactorDc> getCurrentPlayers() {
		return currentUserStore.values();
	}
	
	public CharactorDc getPlayer(String username) {
		return currentUserStore.get(username);
	}

}
