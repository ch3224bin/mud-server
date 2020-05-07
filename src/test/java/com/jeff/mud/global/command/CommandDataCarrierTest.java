package com.jeff.mud.global.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandDataCarrierTest {

	@Test
	public void given_누구_명령_when_Input_생성_then_command_필드에_누구_입력() {
		CommandDataCarrier input = new CommandDataCarrier(null, null, "누구");
		Assertions.assertEquals("누구", input.getCommand());
	}
}
