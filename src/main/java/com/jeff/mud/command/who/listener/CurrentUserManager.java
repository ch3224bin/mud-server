package com.jeff.mud.command.who.listener;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jeff.mud.domain.charactor.domain.CharactorBag;
import com.jeff.mud.global.account.domain.Account;
import com.jeff.mud.state.CharactorState;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Player;
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
		Player player = getPlayerFromDatabase(username);
		player.online();

		currentUserStore.put(username, new CharactorDc(player));
		log.info("Connected : " + username);
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		String username = event.getUser().getName();
		currentUserStore.remove(username);

		Player player = getPlayerFromDatabase(username);
		player.offline();

		log.info("Disconnected : " + username);
	}

	private Player getPlayerFromDatabase(String username) {
		return accountRepository.findByUsername(username)
				.map(account -> playerRepository.findByAccount(account)
					.orElseGet(() -> createPlayer(account)))
				.orElseThrow(() -> new RuntimeException());
	}

	private Player createPlayer(Account account) {
		return playerRepository.save(Player.builder()
			.account(account)
			.state(CharactorState.CHARACTER_CREATE1)
			.name(account.getUsername())
			// TODO 여기다 넣어야 할까..
			.build());
	}

	public Collection<CharactorDc> getCurrentPlayers() {
		return currentUserStore.values();
	}

	public CharactorDc getPlayer(String username) {
		return currentUserStore.get(username);
	}

}
