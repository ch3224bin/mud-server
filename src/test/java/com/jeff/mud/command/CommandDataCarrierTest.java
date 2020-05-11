package com.jeff.mud.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommandDataCarrierTest {

	@Test
	public void given_누구_명령_when_Input_생성_then_command_필드에_누구_입력() {
		CommandDataCarrier input = new CommandDataCarrier(null, null, "누구");
		Assertions.assertEquals("누구", input.getCommand());
	}
	
	@Test
	public void given_동_봐_명령_when_Input_생성_then_command_필드에_봐_입력_그리고_대상_필드에_동_입력() {
		CommandDataCarrier input = new CommandDataCarrier(null, null, "동 봐");
		Assertions.assertEquals("봐", input.getCommand());
		Assertions.assertEquals("동", input.getTarget());
	}
	
	@Test
	public void given_동_봐_명령_when_Input_생성_then_command_hasTarget_true() {
		CommandDataCarrier input = new CommandDataCarrier(null, null, "동 봐");
		Assertions.assertTrue(input.hasTarget());
	}
}
