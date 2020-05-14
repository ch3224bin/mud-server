package com.jeff.mud.domain.charactor.dto;

import com.jeff.mud.domain.charactor.domain.Charactor;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CharactorDc {
	private long id;
	private String name;
	private String flashState;
	private String temp1;
	
	public CharactorDc(Charactor charactor) {
		this.id = charactor.getId();
		this.name = charactor.getName();
	}
	
	public void setFlashState(String flashState) {
		this.flashState = flashState;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
}
