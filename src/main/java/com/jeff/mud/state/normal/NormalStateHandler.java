package com.jeff.mud.state.normal;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.state.StateHandler;

public class NormalStateHandler implements StateHandler {

	@Override
	public boolean handle(Command command, CommandDataCarrier dc) {
		return command.execute(dc);
	}

	@Override
	public CharactorState state() {
		return CharactorState.NORMAL;
	}

}
