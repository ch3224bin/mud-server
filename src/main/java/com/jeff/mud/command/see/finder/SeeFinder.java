package com.jeff.mud.command.see.finder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.command.see.model.NotFoundObject;
import com.jeff.mud.command.see.model.SeeDenyObject;
import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.item.dto.ItemDc;
import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Wayout;
import com.jeff.mud.domain.room.dto.RoomDc;

/**
 * 보이는 물건 찾기
 * 
 * <p>방, 방 안의 물건, 내 소지품, PC, NPC 등 볼 수 있는 것들을 순서대로 찾아나간다.</p>
 * 
 * @author ChangHo Vin
 */
public class SeeFinder {
	
	/**
	 * 아래 Finder 순서대로 찾는다.
	 */
	private final List<Finder<Seeable>> finders = Arrays.asList(
			new DirectionFinder(),
			new ItemInTheRoomFinder());
	
	public Seeable findTarget(CommandDataCarrier input) {
		// TODO 대상에 따라 방 > 출구 > 방의 물품 > 내 소지품 > PC, NPC 등 순으로 찾아나가야함
		if (!input.hasTarget()) {
			return new RoomDc(input.getPlayer().getRoom());
		}
		
		for (Finder<Seeable> finder : finders) {
			Optional<Seeable> result = finder.find(input);
			if (result.isPresent()) {
				return result.get();
			}
		}
		return new NotFoundObject(input.getTarget());
	}
	/**
	 * 가고자 하는 방향 찾기
	 *
	 * @author ChangHo Vin
	 */
	class DirectionFinder implements Finder<Seeable> {

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
				return Optional.of(new SeeDenyObject(String.format("%s 쪽의 문이 잠겨있어 볼 수 없습니다.", wayout.get().getDirection().toString())));
			}
			
			return wayout.map(wo -> new RoomDc(wo.getNextRoom()));
			
		}
	}
	
	/**
	 * 방안의 아이템 찾기
	 * 
	 * @author ChangHo Vin
	 */
	class ItemInTheRoomFinder implements Finder<Seeable> {

		@Override
		public Optional<Seeable> find(CommandDataCarrier input) {
			return input.getPlayer().getRoom()
					.getItem(input.getTarget())
					.map(ItemDc::new);
		}
		
	}
}
