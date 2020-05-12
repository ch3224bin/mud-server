package com.jeff.mud.domain.room;

import org.springframework.stereotype.Component;

import com.jeff.mud.domain.item.service.RoomItemBrokerService;
import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.domain.room.dto.RoomDc;

@Component
public class RoomService {

	private final RoomItemBrokerService roomItemBrokerService;
	
	public RoomService(RoomItemBrokerService roomItemBrokerService) {
		this.roomItemBrokerService = roomItemBrokerService;
	}
	
	public RoomDc getRoomDcWithItems(Room room) {
		return new RoomDc(room, roomItemBrokerService.findItemByRoom(room));
	}
}
