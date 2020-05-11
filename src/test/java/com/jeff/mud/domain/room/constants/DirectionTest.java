package com.jeff.mud.domain.room.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {
	
	@Test
	public void testStringValues() {
		Direction[] values = Direction.values();
		String[] strarr = new String[values.length];
		for (int i = 0, n = values.length; i < n; i++) {
			strarr[i] = values[i].toString();
		}
		
		Assertions.assertArrayEquals(strarr, Direction.stringValues());
	}
}
