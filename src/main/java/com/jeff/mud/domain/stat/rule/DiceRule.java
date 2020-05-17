package com.jeff.mud.domain.stat.rule;

import lombok.Getter;

@Getter
public class DiceRule {
	private int count;
	private int sided;
	private int bonus;
	
	public DiceRule(int count, int sided) {
		this.count = count;
		this.sided = sided;
	}
	
	public DiceRule(int count, int sided, int bonus) {
		this(count, sided);
		this.bonus = bonus;
	}
}
