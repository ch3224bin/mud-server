package com.jeff.mud.domain.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jeff.mud.domain.item.dao.ContainerItemBrokerRepository;
import com.jeff.mud.domain.item.domain.Container;
import com.jeff.mud.domain.item.domain.ContainerItemBroker;
import com.jeff.mud.domain.item.domain.Item;

@Component
public class ContainerItemBrokerService {
	private final ContainerItemBrokerRepository containerItemBrokerRepository;
	
	public ContainerItemBrokerService(ContainerItemBrokerRepository containerItemBrokerRepository) {
		this.containerItemBrokerRepository = containerItemBrokerRepository;
	}
	
	public List<Item> getItemByContainer(Container container) {
		return containerItemBrokerRepository.findByContainer(container).stream()
				.map(ContainerItemBroker::getItem)
				.collect(Collectors.toList());
	}
}
