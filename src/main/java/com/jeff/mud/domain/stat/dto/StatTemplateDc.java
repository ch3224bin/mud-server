package com.jeff.mud.domain.stat.dto;

import java.util.ArrayList;
import java.util.List;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.Status;
import com.jeff.mud.domain.skill.domain.Skill;
import com.jeff.mud.domain.skill.dto.SkillDc;
import com.jeff.mud.domain.stat.domain.Stat;
import com.jeff.mud.domain.stat.rule.DamegeBunusRule;
import com.jeff.mud.domain.stat.rule.StatRuleBook;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StatTemplateDc {
	private String name;
	private List<StatDc> stats = new ArrayList<>();
	private List<SkillDc> skills = new ArrayList<>();
	private int build;
	private int dodge;
	private int luck;
	private int moveRate;
	private int maxMoveRate;
	private int hp;
	private int maxHp;
	private int mp;
	private int maxMp;
	private String dammegeBonus;
	
	public StatTemplateDc(Charactor charactor) {
		this.name = charactor.getName();
		this.dammegeBonus = DamegeBunusRule.getDbString(charactor);
		this.build = StatRuleBook.getBuild(charactor);
		this.dodge = StatRuleBook.getDodge(charactor);
		for (Stat stat : charactor.getStats()) {
			this.stats.add(new StatDc(stat));
			
		}
		for (Skill skill : charactor.getSkills()) {
			this.skills.add(new SkillDc(skill));
		}
		Status status = charactor.getStatus();
		this.hp = status.getHp();
		this.maxHp = status.getMaxHp();
		this.mp = status.getMp();
		this.maxMp = status.getMaxMp();
		this.moveRate = status.getMoveRate();
		this.maxMoveRate = status.getMaxMoveRate();
		this.luck = charactor.getStatus().getLuck();
	}
}
