package com.jeff.mud.domain.stat.rule;

public class Dice {
	private int count;
	private int sided;
	private boolean isMinus;
	
	public Dice(int count, int sided) {
		this.count = count;
		this.sided = sided;
		this.isMinus = false;
	}
	public Dice(int count, int sided, boolean isMinus) {
		this(count, sided);
		this.isMinus = isMinus;
	}
	
	public String toString() {
		if (count == 0 || sided == 0) {
			return "";
		}
		return String.format("%s%dd%d", isMinus ? "-" : "", count, sided);
	}
}
