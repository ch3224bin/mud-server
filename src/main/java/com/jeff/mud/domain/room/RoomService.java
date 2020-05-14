package com.jeff.mud.domain.room;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.domain.charactor.dto.CharactorDc;
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
	
	public RoomDc getRoomDcWithItems(Room room, List<CharactorDc> charactors) {
		return new RoomDc(room, roomItemBrokerService.findItemByRoom(room), charactors);
	}
	
	public RoomDc getRoomDc(Room room, List<CharactorDc> charactors) {
		return new RoomDc(room, Collections.emptyList(), charactors);
	}
}
