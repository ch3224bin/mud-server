package com.jeff.mud.domain.charactor.event;

import org.springframework.context.ApplicationEvent;

import com.jeff.mud.domain.charactor.domain.Status;

public class StatusChangeEvent extends ApplicationEvent {
	private static final long serialVersionUID = 4039299171074991958L;

	public StatusChangeEvent(Status source) {
		super(source);
	}
	
	public Status getCharactor() {
		return (Status) super.getSource();
	}
}
