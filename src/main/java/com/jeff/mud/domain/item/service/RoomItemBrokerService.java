package com.jeff.mud.domain.item.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.jeff.mud.domain.item.dao.RoomItemBrokerRepository;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.domain.RoomItemBroker;
import com.jeff.mud.domain.item.util.ItemUtils;
import com.jeff.mud.domain.room.domain.Room;

@Component
public class RoomItemBrokerService {
	private final RoomItemBrokerRepository roomItemBrokerRepository;
	
	public RoomItemBrokerService(RoomItemBrokerRepository roomItemBrokerRepository) {
		this.roomItemBrokerRepository = roomItemBrokerRepository;
	}
	
	public List<Item> findItemByRoom(Room room) {
		List<RoomItemBroker> itemBrokers = roomItemBrokerRepository.findByRoom(room);
		
		return itemBrokers.stream()
			.map(RoomItemBroker::getItem)
			.collect(Collectors.toList());
	}
	
	public Optional<Item> getItemByName(Room room, String itemName) {
		Stream<Item> stream = this.findItemByRoom(room).stream();
		return ItemUtils.getItemByName(stream, itemName);
	}
}
