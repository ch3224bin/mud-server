package com.jeff.mud.domain.stat.dto;

import com.jeff.mud.domain.stat.domain.Stat;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StatDc {
	private String name;
	private int value;
	
	public StatDc(Stat stat) {
		this.name = stat.name();
		this.value = stat.getValue();
	}
}
