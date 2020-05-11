package com.jeff.mud.command.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.jeff.mud.domain.room.constants.Direction;

import lombok.Getter;

@Getter
public enum CommandConstants {
	who (new String[] {"누구"}),
	see (new String[] {"봐", "보다"}),
	move (Direction.stringValues()),
	get (new String[] {"주워", "줍", "가져"}),
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
