package com.jeff.mud.state;

import com.jeff.mud.command.constants.CommandConstants;

import lombok.Getter;

@Getter
public enum CharactorState {
	CHARACTER_CREATE1 (CommandConstants.noop),
	NORMAL (CommandConstants.see),
	COMBAT (CommandConstants.noop),
	STUN (CommandConstants.noop),
	SHOCK (CommandConstants.noop),
	COMA (CommandConstants.noop),
	DEAD (CommandConstants.noop),
	;
	
	private CommandConstants commandConstants;
	CharactorState (CommandConstants commandConstants) {
		this.commandConstants = commandConstants;
	}
}
