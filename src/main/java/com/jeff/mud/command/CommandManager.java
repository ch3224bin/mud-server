package com.jeff.mud.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.constants.CommandConstants;

@Component
public class CommandManager {
	
	private final Map<CommandConstants, Command> commandMap = new HashMap<>();

	public CommandManager(List<Command> commands) {
		for (Command commmand : commands) {
			commandMap.put(commmand.commandConstants(), commmand);
		}
	}
	
	public Collection<Command> getCommands() {
		return commandMap.values();
	}
	
	public Command getCommand(CommandConstants commandConstants) {
		return commandMap.get(commandConstants);
	}
}
