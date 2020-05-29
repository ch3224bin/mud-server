package com.jeff.mud.state.event;

import org.springframework.context.ApplicationEvent;

public class GameStartEvent extends ApplicationEvent {

	private static final long serialVersionUID = 5129744025964343027L;

	public GameStartEvent(Object source) {
		super(source);
	}

}
