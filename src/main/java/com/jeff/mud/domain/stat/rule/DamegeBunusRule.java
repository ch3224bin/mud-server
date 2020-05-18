package com.jeff.mud.domain.stat.rule;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.stat.constants.Stats;
import com.jeff.mud.domain.stat.domain.Stat;

public class DamegeBunusRule {

	public static String getDbString(Charactor charactor) {
		return getDamegeBonusDice(charactor.getStat(Stats.STR), charactor.getStat(Stats.SIZ)).toString();
	}
	
	public static Dice getDamegeBonusDice(Charactor player) {
		Stat str = player.getStat(Stats.STR);
		Stat siz = player.getStat(Stats.SIZ);
		return getDamegeBonusDice(str, siz);
	}
	
	public static Dice getDamegeBonusDice(Stat str, Stat siz) {
		int sum = str.getValue() + siz.getValue();
		if (sum <= 64) {
			return new Dice(-2);
		} else if (sum <= 84) {
			return new Dice(-1);
		} else if (sum <= 124) {
			return new Dice(0);
		} else if (sum <= 164) {
			return new Dice(1, 4);
		} else if (sum <= 204) {
			return new Dice(1, 6);
		} else {
			int m = (int) Math.ceil((sum - 204) / 80);
			return  new Dice(m, 6);
		}
	}
}
