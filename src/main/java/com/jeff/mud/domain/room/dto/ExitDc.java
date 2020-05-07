package com.jeff.mud.domain.room.dto;

import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Wayout;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExitDc implements Comparable<ExitDc> {
	private long id;
	private long roomId;
	private RoomDc nextRoom;
	private Direction direction;
	private boolean isShow;
	private boolean isLocked;
	
	public ExitDc(Wayout exit) {
		this.id = exit.getId();
		this.roomId = exit.getRoomId();
		this.nextRoom = new RoomDc(exit.getNextRoom());
		this.direction = exit.getDirection();
		this.isShow = exit.isShow();
		this.isLocked = exit.getDoor().isLocked();
	}

	@Override
	public int compareTo(ExitDc o) {
		return Integer.compare(this.direction.getOrder(), o.direction.getOrder());
	}
	
	public String toString() {
		return direction.toString() + (isLocked ? "(잠김)" : "");
	}
}
