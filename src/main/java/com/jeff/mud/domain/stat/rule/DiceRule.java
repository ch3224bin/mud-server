package com.jeff.mud.domain.stat.rule;

import lombok.Getter;

@Getter
public class DiceRule {
	private int count;
	private int sided;
	
	public DiceRule(int count, int sided) {
		this.count = count;
		this.sided = sided;
	}
}
