package com.jeff.mud.domain.charactor.event;

import com.jeff.mud.domain.charactor.domain.Status;
import com.jeff.mud.domain.charactor.dto.StatusDc;
import com.jeff.mud.global.event.DomainEvent;

public class StatusChangeEvent implements DomainEvent<StatusDc> {
	
	private final Status source;
	
	public StatusChangeEvent(Status source) {
		this.source = source;
	}

	@Override
	public StatusDc getSource() {
		return new StatusDc(source);
	}
}
