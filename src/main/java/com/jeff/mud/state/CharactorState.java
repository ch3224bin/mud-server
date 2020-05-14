package com.jeff.mud.state;

import com.jeff.mud.command.constants.CommandConstants;

import lombok.Getter;

@Getter
public enum CharactorState {
	character_create1 (CommandConstants.noop),
	normal (CommandConstants.see),
	combat (CommandConstants.noop)
	;
	
	private CommandConstants commandConstants;
	CharactorState (CommandConstants commandConstants) {
		this.commandConstants = commandConstants;
	}
}
