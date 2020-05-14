package com.jeff.mud.domain.stat.rule;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.stat.constants.StatConstatns;
import com.jeff.mud.domain.stat.domain.Stat;

public class StatRuleBook {
	public static int getIdea(Charactor charactor) {
		return getValue(charactor.getStat(StatConstatns.INT));
	}
	
	public static int getLuck(Charactor charactor) {
		return getValue(charactor.getStat(StatConstatns.POW));
	}
	
	public static int getKnow(Charactor charactor) {
		return getValue(charactor.getStat(StatConstatns.EDU));
	}
	
	public static int getValue(Stat stat) {
		int value = stat.getValue() * 5;
		return Math.min(value, 99); // 99 초과안함
	}
	
	public static int getMaxHp(Charactor charactor) {
		int str = charactor.getStat(StatConstatns.STR).getValue();
		int siz = charactor.getStat(StatConstatns.SIZ).getValue();
		return (str + siz) / 2; 
	}
	
	public static int getMaxMp(Charactor charactor) {
		return charactor.getStat(StatConstatns.POW).getValue();
	}
}
