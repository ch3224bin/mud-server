package com.jeff.mud.command.constants;

import org.junit.jupiter.api.Test;

public class CommandConstantsTest {

	@Test
	public void testMatched() {
		CommandConstants.noop.matched("요");
	}
}
