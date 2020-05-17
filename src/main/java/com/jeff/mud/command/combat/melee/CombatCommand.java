package com.jeff.mud.command.combat.melee;

import com.jeff.mud.combat.CombatManager.CombatZone;
import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.skill.constants.Skills;

public abstract class CombatCommand extends Command {
	@Override
	protected void handle(CommandDataCarrier input) {
		CharactorDc player = getCurrentUserManager().getPlayer(input.getUsername());
		CombatZone combatZone = player.getCombatZone();
		combatZone.putCommand(input.getTarget(), getSkills());
	}
	
	protected abstract CurrentUserManager getCurrentUserManager();
	protected abstract Skills getSkills();
}
