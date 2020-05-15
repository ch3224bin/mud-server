package com.jeff.mud.domain.charactor.dto;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.Status;

import lombok.Getter;

@Getter
public class StatusDc {
	private int hp;
	private int maxHp;
	private int mp;
	private int maxMp;
	
	private CharactorDc charactor;
	
	public StatusDc(Status status) {
		Charactor charactor = status.getCharactor();
		this.charactor = new CharactorDc(charactor);
		this.hp = status.getHp();
		this.maxHp = status.getMaxHp();
		this.mp = status.getMp();
		this.maxMp = status.getMaxMp();
	}
}
