package com.jeff.mud.command.get.finder;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.service.RoomItemBrokerService;
import com.jeff.mud.domain.room.domain.Room;

@Component
public class ItemInTheRoomGetFinder implements Finder<Item> {
	
	private final RoomItemBrokerService roomItemBrokerService;
	
	public ItemInTheRoomGetFinder(RoomItemBrokerService roomItemBrokerService) {
		this.roomItemBrokerService = roomItemBrokerService;
	}

	@Override
	public Optional<Item> find(CommandDataCarrier input) {
		Room room = input.getPlayer().getRoom();
		return roomItemBrokerService.getItemByName(room, input.getTarget());
	}
}
