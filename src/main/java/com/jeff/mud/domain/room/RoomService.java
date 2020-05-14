package com.jeff.mud.domain.room;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.item.service.RoomItemBrokerService;
import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.domain.room.dto.RoomDc;

@Component
public class RoomService {

	private final RoomItemBrokerService roomItemBrokerService;
	private final PlayerRepository playerRepository;
	private final CurrentUserManager currentUserManager;
	
	public RoomService(RoomItemBrokerService roomItemBrokerService, PlayerRepository playerRepository, CurrentUserManager currentUserManager) {
		this.roomItemBrokerService = roomItemBrokerService;
		this.playerRepository = playerRepository;
		this.currentUserManager = currentUserManager;
	}
	
	public RoomDc getRoomDcWithItems(Room room) {
		return new RoomDc(room, roomItemBrokerService.findItemByRoom(room));
	}
	
	public RoomDc getRoomDcWithItemsAndOnlinePlayers(Room room, Player me) {
		List<Player> players = getOnlinePlayers(room, me);
		return new RoomDc(room, roomItemBrokerService.findItemByRoom(room), players);
	}

	public RoomDc getRoomDcWithOnlinePlayers(Room room, Player me) {
		List<Player> players = getOnlinePlayers(room, me);
		return new RoomDc(room, Collections.emptyList(), players);
	}
	
	private List<Player> getOnlinePlayers(Room room, Player me) {
		Set<Long> currentPlayerIds = currentUserManager.getCurrentPlayers().stream()
				.map(CharactorDc::getId)
				.collect(Collectors.toSet());
		
		return playerRepository.findByRoomNotExistsMe(room, me).stream()
				.filter(p -> currentPlayerIds.contains(p.getId()))
				.collect(Collectors.toList());
	}
}
