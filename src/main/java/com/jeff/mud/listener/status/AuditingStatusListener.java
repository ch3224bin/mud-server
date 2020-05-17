package com.jeff.mud.listener.status;

import javax.persistence.PostUpdate;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.jeff.mud.domain.charactor.domain.Status;
import com.jeff.mud.domain.charactor.event.StatusChangeEvent;

@Component
public class AuditingStatusListener {
	
	private final ApplicationEventPublisher applicationEventPublisher;
	
	public AuditingStatusListener(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@PostUpdate
	public void postUpdate(Status status) {
		applicationEventPublisher.publishEvent(new StatusChangeEvent(status));
	}
}
