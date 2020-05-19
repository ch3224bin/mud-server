package com.jeff.mud.domain.skill.constants;

import com.jeff.mud.domain.item.domain.Weapon;

public interface SkillActionable {
	String actionName();
	Skills parentSkill();
	SkillType type();
	Weapon defaultWeapon();
}
