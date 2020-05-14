package com.jeff.mud.domain.stat.rule;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.stat.constants.StatConstatns;
import com.jeff.mud.domain.stat.domain.Stat;

public class DamegeBunusRule {

	public static String getDbString(Charactor charactor) {
		return getDamegeBonusDice(charactor.getStat(StatConstatns.STR), charactor.getStat(StatConstatns.SIZ)).toString();
	}
	
	public static Dice getDamegeBonusDice(Stat str, Stat siz) {
		int sum = str.getValue() + siz.getValue();
		if (sum <= 12) {
			return new Dice(1, 6, true);
		} else if (sum <= 16) {
			return new Dice(1, 4, true);
		} else if (sum <= 24) {
			return new Dice(0, 0);
		} else if (sum <= 32) {
			return new Dice(1, 4);
		} else {
			int m = (int) Math.ceil((sum - 16) / 16);
			return  new Dice(m, 6);
			
		}
	}
}
