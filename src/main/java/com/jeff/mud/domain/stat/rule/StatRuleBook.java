package com.jeff.mud.domain.stat.rule;

import com.jeff.mud.domain.stat.constants.StatConstatns;
import com.jeff.mud.domain.stat.domain.Stat;

public class StatRuleBook {
	public static int getIdea(Stat stat) {
		return getValue(stat, StatConstatns.INT);
	}
	
	public static int getLuck(Stat stat) {
		return getValue(stat, StatConstatns.POW);
	}
	
	public static int getKnow(Stat stat) {
		return getValue(stat, StatConstatns.EDU);
	}
	
	public static int getValue(Stat stat, StatConstatns statConstatns) {
		if (stat.getType() == statConstatns) {
			int value = stat.getValue() * 5;
			return Math.min(value, 99); // 99 초과안함
		}
		return 0;
	}
}
