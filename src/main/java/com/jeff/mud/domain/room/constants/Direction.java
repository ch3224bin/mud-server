package com.jeff.mud.domain.room.constants;

import lombok.Getter;

@Getter
public enum Direction {
	동 (1),
	서 (2),
	남 (3),
	북 (4)
	;
	
	private int order;
	Direction(int order) {
		this.order = order;
	}
}
