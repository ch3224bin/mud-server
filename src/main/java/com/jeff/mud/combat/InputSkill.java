package com.jeff.mud.combat;

import com.google.common.base.Strings;
import com.jeff.mud.domain.skill.constants.SkillAction;

import lombok.Getter;

@Getter
public class InputSkill {
	private final String target;
	private final SkillAction action;
	
	public InputSkill(String target, SkillAction action) {
		this.target = target;
		this.action = action;
	}
	
	public boolean hasTarget() {
		return Strings.isNullOrEmpty(target);
	}
}
