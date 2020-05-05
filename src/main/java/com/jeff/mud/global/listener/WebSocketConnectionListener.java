package com.jeff.mud.global.listener;

import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public interface WebSocketConnectionListener {
	void connected(SessionConnectedEvent event);
	void disconnect(SessionDisconnectEvent event);
}
