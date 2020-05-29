package com.jeff.mud.state;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.CommandManager;
import com.jeff.mud.command.constants.CommandConstants;

@Component
public class PlayerStateHandler {
	private final CommandManager commandManager;
	private final StateHandlerManager stateHandlerManager;
	
	public PlayerStateHandler(CommandManager commandManager, StateHandlerManager stateHandlerManager) {
		this.commandManager = commandManager;
		this.stateHandlerManager = stateHandlerManager;
	}
	
	public boolean handle(CharactorState state, Command command, CommandDataCarrier dc) {
		StateHandler stateHandler = stateHandlerManager.getStateHandler(state);
		if (stateHandler != null) {
			return stateHandler.handle(command, dc);
		}
		
		return command.execute(dc);
	}
	
	public boolean handle(CharactorState state, CommandConstants commandConstants, CommandDataCarrier dc) {
		return handle(state, commandManager.getCommand(commandConstants), dc);
	}
}
