package com.jeff.mud.command.who.dto;

import java.util.Collection;
import java.util.Set;

import com.jeff.mud.domain.player.dto.PlayerDc;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WhoDc {
	private Set<PlayerDc> players;
	
	public WhoDc(Collection<PlayerDc> players) {
		this.players = Set.copyOf(players);
	}
}
