package com.jeff.mud.command.see.finder;

import java.util.Optional;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.command.see.model.SeeDenyObject;
import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.room.RoomService;
import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Wayout;

/**
 * 가고자 하는 방향 찾기
 *
 * @author ChangHo Vin
 */
@Component
@Order(1)
public class DirectionSeeFinder implements Finder<Seeable> {
	
	private RoomService roomService;
	
	public DirectionSeeFinder(RoomService roomService) {
		this.roomService = roomService;
	}
	
	@Override
	public Optional<Seeable> find(CommandDataCarrier input) {
		String target = input.getTarget();
		if (!Direction.contains(target)) {
			return Optional.empty();
		}
		
		Direction direction = Direction.valueOf(target);
		Optional<Wayout> wayout = input.getPlayer().getRoom().getWayoutByDirection(direction);
		
		if (wayout.isEmpty() || !wayout.get().isShow()) {
			return Optional.empty();
		}
		
		if (wayout.get().getDoor().isLocked()) {
			// TODO 문을 보여줄지..
			return Optional.of(new SeeDenyObject(String.format("%s쪽의 문이 잠겨있어 볼 수 없습니다.", wayout.get().getDirection().toString())));
		}
		
		return wayout.map(wo -> roomService.getRoomDcWithOnlinePlayers(wo.getNextRoom(), input.getPlayer()));
		
	}
}
