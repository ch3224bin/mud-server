package com.jeff.mud.domain.stat.rule;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.stat.constants.Stats;
import com.jeff.mud.domain.stat.domain.Stat;

public class StatRuleBook {
	public static int getIdea(Charactor charactor) {
		return getValue(charactor.getStat(Stats.INT));
	}
	
	public static int getLuck(Charactor charactor) {
		return getValue(charactor.getStat(Stats.POW));
	}
	
	public static int getKnow(Charactor charactor) {
		return getValue(charactor.getStat(Stats.EDU));
	}
	
	public static int getValue(Stat stat) {
		int value = stat.getValue() * 5;
		return Math.min(value, 99); // 99 초과안함
	}
	
	public static int getMaxHp(Charactor charactor) {
		int str = charactor.getStat(Stats.STR).getValue();
		int siz = charactor.getStat(Stats.SIZ).getValue();
		return (str + siz) / 2; 
	}
	
	public static int getMaxMp(Charactor charactor) {
		return charactor.getStat(Stats.POW).getValue();
	}
}
