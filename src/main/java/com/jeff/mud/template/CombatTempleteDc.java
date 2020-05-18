package com.jeff.mud.template;

import lombok.Getter;

@Getter
public class CombatTempleteDc {
	private String attacker;
	private String defender;
	private String message;
	private int damege;
	
	public CombatTempleteDc(String attacker, String defender) {
		this.attacker = attacker;
		this.defender = defender;
	}
	
	public CombatTempleteDc(String attacker, String defender, String message) {
		this(attacker, defender);
		this.message = message;
	}
	
	public CombatTempleteDc(String attacker, String defender, String message, int damege) {
		this(attacker, defender, message);
		this.damege = damege;
	}
}
