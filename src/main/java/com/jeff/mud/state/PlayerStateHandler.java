package com.jeff.mud.state;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.CommandManager;
import com.jeff.mud.command.constants.CommandConstants;

@Component
public class PlayerStateHandler {
	private final CommandManager commandManager;
	
	public PlayerStateHandler(CommandManager commandManager) {
		this.commandManager = commandManager;
	}
	
	public boolean handle(CharactorState state, Command command, CommandDataCarrier dc) {
		StateHandler stateHandler = StateHandlerManager.getStateHandler(state);
		if (stateHandler != null) {
			return stateHandler.handle(command, dc);
		}
		
		return command.execute(dc);
	}
	
	public boolean handle(CharactorState state, CommandConstants commandConstants, CommandDataCarrier dc) {
		return handle(state, commandManager.getCommand(commandConstants), dc);
	}
}
