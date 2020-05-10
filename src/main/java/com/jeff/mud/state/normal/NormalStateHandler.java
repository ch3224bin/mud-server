package com.jeff.mud.state.normal;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.state.StateHandler;

public class NormalStateHandler implements StateHandler {

	@Override
	public boolean handle(Command command, CommandDataCarrier dc) {
		return command.execute(dc);
	}

	@Override
	public PlayerState state() {
		return PlayerState.normal;
	}

}
