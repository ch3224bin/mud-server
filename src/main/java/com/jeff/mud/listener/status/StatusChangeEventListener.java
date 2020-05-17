package com.jeff.mud.listener.status;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jeff.mud.domain.charactor.dto.StatusDc;
import com.jeff.mud.domain.charactor.event.StatusChangeEvent;
import com.jeff.mud.global.message.CustomMessagingTemplate;

@Component
public class StatusChangeEventListener {
	
	private final CustomMessagingTemplate customMessagingTemplate;

	public StatusChangeEventListener(CustomMessagingTemplate customMessagingTemplate) {
		this.customMessagingTemplate = customMessagingTemplate;
	}
	
	@Async
	@TransactionalEventListener
	public void statusChanged(StatusChangeEvent event) {
		StatusDc status = event.getSource();
		if (!status.getCharactor().isNpc()) {
			customMessagingTemplate.sendShortStatusToYou(status.getCharactor().getUsername(), status);
		}
	}
}
