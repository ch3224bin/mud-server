package com.jeff.mud.command.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

@Getter
public enum CommandConstants {
	WHO (new String[]{"누구"}, "who")
	;
	
	private String location;
	private Set<String> commands;

	CommandConstants(String[] commands, String location) {
		this.commands = new HashSet<>(Arrays.asList(commands));
		this.location = location;
	}
	
	public boolean matched(String command) {
		return commands.stream()
					.anyMatch(c -> c.startsWith(command));
	}
}
