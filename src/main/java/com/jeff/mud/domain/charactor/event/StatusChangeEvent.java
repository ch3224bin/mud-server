package com.jeff.mud.domain.charactor.event;

import com.jeff.mud.domain.charactor.domain.Status;
import com.jeff.mud.domain.charactor.dto.StatusDc;
import org.springframework.context.ApplicationEvent;

public class StatusChangeEvent extends ApplicationEvent {
	public StatusChangeEvent(Status source) {
		super(source);
	}

	@Override
	public StatusDc getSource() {
		return new StatusDc((Status) super.getSource());
	}
}
