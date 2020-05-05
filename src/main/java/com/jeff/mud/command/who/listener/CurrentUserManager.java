package com.jeff.mud.command.who.listener;

import java.security.Principal;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.jeff.mud.global.listener.WebSocketConnectionEventListener;
import com.jeff.mud.global.listener.WebSocketConnectionListener;

import lombok.extern.slf4j.Slf4j;

/**
 * 현재 접속자 관리
 * 
 * WebSocketConnectionEventListener에 등록하여 현재 접속자를 Map으로 관리한다. 
 * 
 * @author ch3224bin
 * @see com.jeff.mud.global.listener.WebSocketConnectionEventListener
 */
@Slf4j
@Component
public class CurrentUserManager implements WebSocketConnectionListener {
	
	Set<String> currentUserSet = ConcurrentHashMap.newKeySet();
	
	public CurrentUserManager(WebSocketConnectionEventListener webSocketConnectionEventListener) {
		webSocketConnectionEventListener.addListener(this);
	}

	@Override
	public void connected(SessionConnectedEvent event) {
//		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		Principal user = event.getUser();
		log.info("Connected : " + user.getName());
		currentUserSet.add(user.getName());
	}

	@Override
	public void disconnect(SessionDisconnectEvent event) {
		Principal user = event.getUser();
		log.info("Disconnected : " + user.getName());
		currentUserSet.remove(user.getName());
	}

}
