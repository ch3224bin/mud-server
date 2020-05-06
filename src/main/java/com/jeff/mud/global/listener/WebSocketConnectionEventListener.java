package com.jeff.mud.global.listener;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketConnectionEventListener {
	
	private final List<WebSocketConnectionListener> listeners;
	
	public WebSocketConnectionEventListener(List<WebSocketConnectionListener> listeners) {
		this.listeners = listeners;
	}

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		listeners.parallelStream()
			.forEach(listener -> listener.connected(event));
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		listeners.parallelStream()
			.forEach(listener -> listener.disconnect(event));
	}
}
