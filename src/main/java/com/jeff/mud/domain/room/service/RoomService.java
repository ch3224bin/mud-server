package com.jeff.mud.domain.room.service;

import java.util.Collections;
import java.util.List;

import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.exception.RoomNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.item.service.RoomItemBrokerService;
import com.jeff.mud.domain.room.dao.RoomRepository;
import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.domain.room.dto.RoomDc;

@Transactional
@Component
public class RoomService {

	private final RoomItemBrokerService roomItemBrokerService;
	private final RoomRepository roomRepository;
	
	public RoomService(RoomItemBrokerService roomItemBrokerService, RoomRepository roomRepository) {
		this.roomItemBrokerService = roomItemBrokerService;
		this.roomRepository = roomRepository;
	}
	
	public RoomDc getRoomDcWithItems(Room room, List<CharactorDc> charactors) {
		return new RoomDc(room, roomItemBrokerService.findItemByRoom(room), charactors);
	}
	
	public RoomDc getRoomDc(Room room, List<CharactorDc> charactors) {
		return new RoomDc(room, Collections.emptyList(), charactors);
	}

	public RoomDc getRoom(final long id) {
		return roomRepository.findById(id)
				.map(RoomDc::new)
				.orElseThrow(() -> new RoomNotFoundException(id));
	}

	public RoomDc updateRoom(long id, RoomDc req) {
		return roomRepository.findById(id)
			.map(room -> {
				room.setSummary(req.getSummary());
				room.setDescription(room.getDescription());
				return new RoomDc(room);
			})
			.orElseThrow(() -> new RoomNotFoundException(id));
	}

	public RoomDc createRoom(RoomDc req) {
		return new RoomDc(roomRepository.save(req.toEntity()));
	}

	public Page<RoomDc> getPagedRooms(Pageable pageable) {
		return roomRepository.findAll(pageable)
			.map(RoomDc::new);
	}

	public List<Room> linkAnotherRoom(long roomId1, long roomId2, Direction dir1, Direction dir2) {
		Room room1 = roomRepository.findById(roomId1).orElseThrow(() -> new RoomNotFoundException(roomId1));
		Room room2 = roomRepository.findById(roomId2).orElseThrow(() -> new RoomNotFoundException(roomId2));
		room1.linkAnotherRoom(room2, dir1, dir2);
		return List.of(room1, room2);
	}
}
