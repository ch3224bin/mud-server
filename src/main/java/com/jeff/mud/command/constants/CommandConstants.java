package com.jeff.mud.command.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

@Getter
public enum CommandConstants {
	who (new String[] {"누구"}),
	see (new String[] {"봐", "보다"}),
	noop (new String[] {}) // 아무것도 하지 않는다.
	;
	
	private Set<String> commands;

	CommandConstants(String[] commands) {
		this.commands = new HashSet<>(Arrays.asList(commands));
	}
	
	public boolean matched(String command) {
		return commands.stream()
					.anyMatch(c -> c.startsWith(command));
	}
}
