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
		// TODO item이 컨테이너 일때, 가운데 수식어를 가져와서 컨테이너에서 꺼냄, 죽은 NPC도 컨테이너
		return roomItemBrokerService.getItemByName(room, input.getTarget());
	}
}
