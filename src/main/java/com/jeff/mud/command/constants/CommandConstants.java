package com.jeff.mud.command.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.jeff.mud.domain.room.constants.Direction;

import lombok.Getter;

@Getter
public enum CommandConstants {
	who (new String[] {"누구"}),
	stat (new String[] {"상태", "스탯창"}),
	say (new String[] {"말"}),
	see (new String[] {"봐", "보다"}),
	bag (new String[] {"소지품"}),
	move (Direction.stringValues()),
	get (new String[] {"주워", "줍", "가져"}),
	drop (new String[] {"버려"}),
	takeout (new String[] {"꺼내", "빼"}),
	put (new String[] {"넣어"}),
	unlock (new String[] {"열어"}),
	lock (new String[] {"잠궈"}),
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
