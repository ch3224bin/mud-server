package com.jeff.mud.command.noop.command;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.state.PlayerState;

@Component
public class NoopCommand implements Command {

	@Override
	public List<PlayerState> allowStates() {
		return Collections.emptyList();
	}

	@Override
	public CommandConstants commandConstants() {
		return CommandConstants.noop;
	}

	@Override
	public void handle(CommandDataCarrier input) {
		
	}

	@Override
	public void handleDenyState(PlayerState state) {
		
	}

}
