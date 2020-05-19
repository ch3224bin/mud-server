package com.jeff.mud.domain.skill.constants;

import java.util.Set;

import com.jeff.mud.domain.item.domain.Weapon;

public interface SkillActionable {
	String actionName();
	Set<Skills> parentSkills();
	SkillType type();
	Weapon defaultWeapon();
}
