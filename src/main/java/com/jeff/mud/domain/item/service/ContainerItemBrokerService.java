package com.jeff.mud.domain.item.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jeff.mud.domain.item.dao.ContainerItemBrokerRepository;
import com.jeff.mud.domain.item.dao.RoomItemBrokerRepository;
import com.jeff.mud.domain.item.domain.Container;
import com.jeff.mud.domain.item.domain.ContainerItemBroker;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.domain.ItemBroker;
import com.jeff.mud.domain.item.domain.RoomItemBroker;
import com.jeff.mud.domain.item.util.ItemUtils;
import com.jeff.mud.domain.room.domain.Room;

@Component
public class ContainerItemBrokerService {
	private final ContainerItemBrokerRepository containerItemBrokerRepository;
	private final RoomItemBrokerRepository roomItemBrokerRepository;
	
	public ContainerItemBrokerService(ContainerItemBrokerRepository containerItemBrokerRepository, RoomItemBrokerRepository roomItemBrokerRepository) {
		this.containerItemBrokerRepository = containerItemBrokerRepository;
		this.roomItemBrokerRepository = roomItemBrokerRepository;
	}
	
	public List<Item> getItemsByContainer(Container container) {
		return containerItemBrokerRepository.findByContainer(container).stream()
				.map(ContainerItemBroker::getItem)
				.collect(Collectors.toList());
	}
	
	public Optional<Item> getItemByContainer(Container container, String itemName) {
		return ItemUtils.getItemByName(getItemsByContainer(container).stream(), itemName);
	}
	
	public Optional<Container> getContainerByRoom(Room room) {
		List<RoomItemBroker> itemBrokers = roomItemBrokerRepository.findByRoom(room);
		return itemBrokers.stream()
			.map(ItemBroker::getItem)
			.filter(item -> item instanceof Container)
			.map(item -> (Container) item)
			.findFirst();
	}
}
