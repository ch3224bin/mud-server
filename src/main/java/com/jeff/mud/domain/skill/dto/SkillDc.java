package com.jeff.mud.domain.skill.dto;

import com.jeff.mud.domain.skill.domain.Skill;

import lombok.Getter;

@Getter
public class SkillDc {
	private String name;
	private int point;

	public SkillDc(Skill skill) {
		this.name = skill.getType().getSkillName();
		this.point = skill.getPoint();
	}
}
