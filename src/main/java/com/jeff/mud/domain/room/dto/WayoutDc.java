package com.jeff.mud.domain.room.dto;

import com.jeff.mud.domain.room.domain.Wayout;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WayoutDc {
	private long id;
	private long roomId;
	private long nextRoomId;
	private String direction;
	private boolean isShow;
	private boolean isLocked;
	
	public WayoutDc(Wayout wayout) {
		this.id = wayout.getId();
		this.roomId = wayout.getRoom().getId();
		this.nextRoomId = wayout.getNextRoom().getId();
		this.direction = wayout.getDirection().getName();
		this.isShow = wayout.isShow();
		this.isLocked = wayout.getDoor().isLocked();
	}
}
