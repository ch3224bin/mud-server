package com.jeff.mud.command.noop.command;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.state.PlayerState;

@Component
public class NoopCommand extends Command {

	@Override
	protected List<PlayerState> allowStates() {
		return Collections.emptyList();
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.noop;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
