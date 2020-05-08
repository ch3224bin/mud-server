package com.jeff.mud.domain.player.dto;

import com.jeff.mud.domain.player.domain.Player;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PlayerDc {
	private long id;
	private String name;
	private String flashState;
	
	public PlayerDc(Player player) {
		this.id = player.getId();
		this.name = player.getName();
	}
	
	public void setFlashState(String flashState) {
		this.flashState = flashState;
	}
}
