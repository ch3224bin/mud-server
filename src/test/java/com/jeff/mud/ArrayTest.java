package com.jeff.mud;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayTest {

	@Test
	public void testCopyOfRange() {
		String[] words = new String[] {"1", "2", "3", "4"};
		int length = words.length;
		String result = String.join(" ", Arrays.copyOfRange(words, 2, length - 1));
		Assertions.assertEquals("3", result);
		
		words = new String[] {"1", "2", "3", "4", "5"};
		length = words.length;
		result = String.join(" ", Arrays.copyOfRange(words, 2, length - 1));
		Assertions.assertEquals("3 4", result);
	}
}
