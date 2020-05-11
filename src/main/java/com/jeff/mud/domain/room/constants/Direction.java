package com.jeff.mud.domain.room.constants;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum Direction {
	동,
	서,
	남,
	북
	;
	
	public static boolean contains(String str) {
	    for (Direction c : Direction.values()) {
	        if (c.name().equals(str)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static String[] stringValues() {
		return Arrays.asList(Direction.values()).stream()
				.map(x -> x.toString())
				.toArray(String[]::new);
	}
}
