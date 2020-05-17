package com.jeff.mud.domain.item.constants;

import com.jeff.mud.combat.constants.Judgments;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.skill.constants.Skills;

public interface WeaponDefinition {
	Weapon createWeapon();
	Skills weaponSkill();
	String getMessage(Judgments judgments);
}
