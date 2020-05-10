package com.jeff.mud.domain.room.dto;

import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Wayout;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WayoutDc implements Comparable<WayoutDc> {
	private long id;
	private long roomId;
	private RoomDc nextRoom;
	private Direction direction;
	private boolean isShow;
	private boolean isLocked;
	
	public WayoutDc(Wayout wayout) {
		this.id = wayout.getId();
		this.roomId = wayout.getRoomId();
		this.nextRoom = new RoomDc(wayout.getNextRoom());
		this.direction = wayout.getDirection();
		this.isShow = wayout.isShow();
		this.isLocked = wayout.getDoor().isLocked();
	}

	@Override
	public int compareTo(WayoutDc o) {
		return Integer.compare(this.direction.getOrder(), o.direction.getOrder());
	}
	
	public String toString() {
		return direction.toString() + (isLocked ? "(잠김)" : "");
	}
}
