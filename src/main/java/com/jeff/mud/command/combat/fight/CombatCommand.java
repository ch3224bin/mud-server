package com.jeff.mud.command.combat.fight;

import com.jeff.mud.combat.model.CombatZone;
import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.skill.constants.SkillAction;
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
		combatZone.putCommand(input.getTarget(), getSkillAction());
	}
	
	protected abstract CurrentUserManager getCurrentUserManager();
	protected abstract CustomMessagingTemplate getMessagingTemplate();
	protected abstract SkillAction getSkillAction();
	protected abstract boolean validate(CommandDataCarrier input);
}
