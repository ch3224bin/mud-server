package com.jeff.mud.combat.constants;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.stat.rule.DamegeBunusRule;
import com.jeff.mud.domain.stat.rule.Dice;

public enum Judgments implements AttackPointCalculable {
	SUCCESS {
		@Override
		public int getDamege(Charactor attacker, Weapon weapon) {
			Dice attackRoll = new Dice(weapon.getDiceRule());
        	Dice dbRoll = DamegeBunusRule.getDamegeBonusDice(attacker);
        	
        	return attackRoll.getValue() + weapon.getBonus() + dbRoll.getValue();
		}
	},
	FAIL {
		@Override
		public int getDamege(Charactor attacker, Weapon weapon) {
			return 0;
		}
	},
	HARD {
		@Override
		public int getDamege(Charactor attacker, Weapon weapon) {
			return SUCCESS.getDamege(attacker, weapon);
		}
	},
	EXTREME {
		@Override
		public int getDamege(Charactor attacker, Weapon weapon) {
			Dice attackRoll = new Dice(weapon.getDiceRule());
        	Dice dbRoll = DamegeBunusRule.getDamegeBonusDice(attacker);
        	
        	int damege = attackRoll.getMaxValue() + weapon.getBonus() + dbRoll.getMaxValue();
        	if (weapon.isCritical()) {
        		damege += new Dice(weapon.getDiceRule()).getValue();
        	}
        	
        	return damege;
		}
	},
	FUMBLE {
		@Override
		public int getDamege(Charactor attacker, Weapon weapon) {
			return 0;
		}
	},
	CRITICAL {
		@Override
		public int getDamege(Charactor attacker, Weapon weapon) {
			return EXTREME.getDamege(attacker, weapon);
		}
	},
	;
}
