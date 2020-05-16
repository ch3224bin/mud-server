package com.jeff.mud.domain.item.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.mud.domain.charactor.domain.Status;
import com.jeff.mud.domain.charactor.repository.StatusRepository;

@Component
public class StatusService {
	
	private final StatusRepository statusRepository;
	
	public StatusService(StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}
	
	@Async
	@Transactional
	public void save(Status status) {
		statusRepository.save(status);
	}
}
