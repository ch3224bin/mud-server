package com.jeff.mud.domain.charactor.event;

import org.springframework.context.ApplicationEvent;

import com.jeff.mud.domain.charactor.domain.Status;
import com.jeff.mud.domain.charactor.dto.StatusDc;

@SuppressWarnings("serial")
public class StatusChangeEvent extends ApplicationEvent {
	public StatusChangeEvent(Status source) {
		super(source);
	}

	@Override
	public StatusDc getSource() {
		return new StatusDc((Status) super.getSource());
	}
}
