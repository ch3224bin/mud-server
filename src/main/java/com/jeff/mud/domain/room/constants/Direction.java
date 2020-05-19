package com.jeff.mud.domain.room.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.Sets;

import lombok.Getter;

@Getter
public enum Direction {
	EAST ("동", Sets.newHashSet("동", "ㄷ")),
	WEST ("서", Sets.newHashSet("서", "ㅅ")),
	SOUTH ("남", Sets.newHashSet("남", "ㄴ")),
	NORTH ("북", Sets.newHashSet("북", "ㅂ"))
	;
	
	private static final Map<String, Direction> dirMap = new ConcurrentHashMap<>();
	private final Set<String> synonyms;
	private final String name;
	Direction(String name, Set<String> synonyms) {
		this.name = name;
		this.synonyms = synonyms;
	}
	
	public static boolean contains(String str) {
	    for (Direction c : Direction.values()) {
	        if (c.getSynonyms().contains(str)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static Direction valueOfString(String value) {
		if (dirMap.size() == 0) {
			for (Direction c : Direction.values()) {
		        for (String s : c.getSynonyms()) {
		        	dirMap.put(s, c);
		        }
		    }
		}
		return dirMap.get(value);
	}
	
	public static String[] stringValues() {
		return Arrays.asList(Direction.values()).stream()
				.flatMap(x -> x.synonyms.stream())
				.toArray(String[]::new);
	}
}
