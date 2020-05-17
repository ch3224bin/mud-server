package com.jeff.mud.domain.skill.constants;

import com.jeff.mud.domain.item.domain.Weapon;

public interface Skillable {
	String getName();
	SkillType getType();
	Weapon defaultWeapon();
}
