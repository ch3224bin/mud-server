package com.jeff.mud.domain.stat.rule;

import java.util.Random;

import lombok.Getter;

@Getter
public class Dice {
	private Random random = new Random();
	
	private int count;
	private int sided;
	private boolean isMinus;
	private int value;
	
	public Dice(int count, int sided) {
		this.count = count;
		this.sided = sided;
		this.isMinus = false;
		if (sided == 0 || count == 0) {
			this.value = 0;
		} else {
			this.value = (random.nextInt(sided) + 1) * count;
		}
	}
	
	public Dice(DiceRule diceRule) {
		this(diceRule.getCount(), diceRule.getSided());
	}
	
	public Dice(int value) {
		this.value = value;
	}
	
	public Dice(int count, int sided, boolean isMinus) {
		this(count, sided);
		this.isMinus = isMinus;
	}
	
	public int getMaxValue() {
		return count * sided;
	}
	
	public String toString() {
		if (count == 0 || sided == 0) {
			return String.valueOf(value);
		}
		return String.format("%s%dd%d", isMinus ? "-" : "", count, sided);
	}
}
