package com.jeff.mud.state;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;

public interface StateHandler {
	boolean handle(Command command, CommandDataCarrier dc);
	CharactorState state();
}
