package com.jeff.mud.command.combat.melee;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.template.Template;

public abstract class WeaponCombatCommand extends CombatCommand {
	@Override
	protected boolean validate(CommandDataCarrier input) {
		// 착용 무기에 해당하는 스킬만 가능 
		Weapon weapon = input.getPlayer().getEquipment().getWeapon();
		if (weapon.getType().weaponSkill() != getSkills()) {
			getMessagingTemplate().sendToYou(input.getUsername(), Template.defaultMessage, "착용한 무기로 " + getSkills().getName() + "을(를) 할 수 없습니다.");
			return false;
		}
		
		return true;
	}
}
