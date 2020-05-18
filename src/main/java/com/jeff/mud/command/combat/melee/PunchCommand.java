package com.jeff.mud.command.combat.melee;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.skill.constants.Skills;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;

@Component
public class PunchCommand extends WeaponCombatCommand {
	
	private final CurrentUserManager currentUserManager;
	private final CustomMessagingTemplate customMessagingTemplate;
	
	public PunchCommand(CurrentUserManager currentUserManager, CustomMessagingTemplate customMessagingTemplate) {
		this.currentUserManager = currentUserManager;
		this.customMessagingTemplate = customMessagingTemplate;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.combat);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.punch;
	}
	
	protected Skills getSkills() {
		return Skills.fight_brawl;
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

	@Override
	protected CurrentUserManager getCurrentUserManager() {
		return currentUserManager;
	}

	@Override
	protected CustomMessagingTemplate getMessagingTemplate() {
		return customMessagingTemplate;
	}

}
