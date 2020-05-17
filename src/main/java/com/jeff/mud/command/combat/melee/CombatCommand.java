package com.jeff.mud.command.combat.melee;

import com.jeff.mud.combat.CombatManager.CombatZone;
import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.skill.constants.Skills;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.template.Template;

public abstract class CombatCommand extends Command {
	
	@Override
	protected void handle(CommandDataCarrier input) {
		CharactorDc sessionPlayer = getCurrentUserManager().getPlayer(input.getUsername());
		CombatZone combatZone = sessionPlayer.getCombatZone();
		if (combatZone.hasInputSkill() || !combatZone.isMyTurn(input.getUsername())) {
			getMessagingTemplate().sendToYou(input.getUsername(), Template.defaultMessage, "지금은 기술을 사용할 수 없습니다.");
			return;
		}
		if (!validate(input)) {
			return;
		}
		combatZone.putCommand(input.getTarget(), getSkills());
	}
	
	protected abstract CurrentUserManager getCurrentUserManager();
	protected abstract CustomMessagingTemplate getMessagingTemplate();
	protected abstract Skills getSkills();
	protected abstract boolean validate(CommandDataCarrier input);
}
