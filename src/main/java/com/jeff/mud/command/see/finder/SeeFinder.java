package com.jeff.mud.command.see.finder;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.command.see.model.NotFoundObject;
import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.item.service.RoomItemBrokerService;
import com.jeff.mud.domain.room.RoomService;

/**
 * 보이는 물건 찾기
 * 
 * <p>방, 방 안의 물건, 내 소지품, PC, NPC 등 볼 수 있는 것들을 순서대로 찾아나간다.</p>
 * 
 * @author ChangHo Vin
 */
@Component
public class SeeFinder {
	
	private final List<Finder<Seeable>> finders;
	private final RoomService roomService;
	
	public SeeFinder(List<Finder<Seeable>> finders, RoomItemBrokerService roomItemBrokerService, RoomService roomService) {
		this.finders = finders;
		this.roomService = roomService;
	}
	
	public Seeable findTarget(CommandDataCarrier input) {
		if (!input.hasTarget()) {
			return roomService.getRoomDcWithItemsAndOnlinePlayers(input.getPlayer().getRoom(), input.getPlayer());
		}
		
		for (Finder<Seeable> finder : finders) {
			Optional<Seeable> result = finder.find(input);
			if (result.isPresent()) {
				return result.get();
			}
		}
		return new NotFoundObject(input.getTarget());
	}
}
