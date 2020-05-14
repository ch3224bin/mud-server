package com.jeff.mud.domain.stat.dto;

import java.util.ArrayList;
import java.util.List;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.Status;
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
	private int idea;
	private int luck;
	private int know;
	private int hp;
	private int maxHp;
	private int mp;
	private int maxMp;
	private String dammegeBonus;
	
	public StatTemplateDc(Charactor charactor) {
		this.name = charactor.getName();
		this.dammegeBonus = DamegeBunusRule.getDbString(charactor);
		this.idea = StatRuleBook.getIdea(charactor);
		this.luck = StatRuleBook.getLuck(charactor);
		this.know = StatRuleBook.getKnow(charactor);
		for (Stat stat : charactor.getStats()) {
			this.stats.add(new StatDc(stat));
			Status status = charactor.getStatus();
			this.hp = status.getHp();
			this.maxHp = status.getMaxHp();
			this.mp = status.getMp();
			this.maxMp = status.getMaxMp();
		}
	}
}
