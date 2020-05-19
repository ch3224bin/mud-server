package com.jeff.mud.combat;

import com.jeff.mud.combat.constants.Judgments;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.skill.domain.Skill;
import com.jeff.mud.domain.stat.rule.Dice;

import lombok.Getter;

@Getter
public class Judgment {
	private final int point;
	private final Dice dice;
	private final Judgments judgments;
	
	public Judgment (Skill playerSkill, Weapon weapon) {
    	if (playerSkill != null) {
    		point = playerSkill.getPoint();
    		dice = new Dice(1, playerSkill.getPoint());
    	} else {
    		point = weapon.getAccuracy();
    		dice = new Dice(1, weapon.getAccuracy());
    	}
    	
    	int value = dice.getValue();
		if (value <= (point/5)) {
    		judgments = Judgments.EXTREME;
    	} else if (value <= point) {
    		judgments = Judgments.SUCCESS;
    	} else if ((point < 50 && value >= 96 && value <= 100) ||
    			value == 100) {
    		judgments = Judgments.FUMBLE;
    	} else {
    		judgments = Judgments.FAIL;
    	}
	}
}
