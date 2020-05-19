package com.jeff.mud.domain.stat.rule;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.stat.constants.Stats;

public class StatRuleBook {
	
	public static int getMaxHp(Charactor charactor) {
		int siz = charactor.getStat(Stats.SIZ).getValue();
		int con = charactor.getStat(Stats.CON).getValue();
		return (siz + con) / 5; 
	}
	
	public static int getMaxMp(Charactor charactor) {
		return charactor.getStat(Stats.POW).getValue() / 5;
	}

	// 체구
	public static int getBuild(Charactor charactor) {
		int str = charactor.getStat(Stats.STR).getValue();
		int siz = charactor.getStat(Stats.SIZ).getValue();
		int sum = str + siz;
		if (sum <= 64) {
			return -2;
		} else if (sum <= 84) {
			return -1;
		} else if (sum <= 124) {
			return 0;
		} else if (sum <= 164) {
			return 1;
		} else if (sum <= 204) {
			return 2;
		} else {
			int m = (int) Math.ceil((sum - 204) / 80);
			return 2 + m;
		}
	}
	
	// 회피
	public static int getDodge(Charactor charactor) {
		int dex = charactor.getStat(Stats.DEX).getValue();
		return dex / 2;
	}
	
	// 이동력
	public static int getMaxMoveRate(Charactor charactor) {
		int str = charactor.getStat(Stats.STR).getValue();
		int siz = charactor.getStat(Stats.SIZ).getValue();
		int dex = charactor.getStat(Stats.DEX).getValue();
		
		if (dex < siz && str < siz) {
			return 7;
		}
		if (dex > siz && str > siz) {
			return 9;
		}
		
		return 8;
	}
}
