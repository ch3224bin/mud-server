package com.jeff.mud.command.combat.melee;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.skill.constants.Skills;
import com.jeff.mud.state.CharactorState;

@Component
public class FistCommand extends CombatCommand {
	
	private final CurrentUserManager currentUserManager;
	
	public FistCommand(CurrentUserManager currentUserManager) {
		this.currentUserManager = currentUserManager;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.combat);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.fist;
	}
	
	protected Skills getSkills() {
		return Skills.fist;
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

	@Override
	protected CurrentUserManager getCurrentUserManager() {
		return currentUserManager;
	}

}
