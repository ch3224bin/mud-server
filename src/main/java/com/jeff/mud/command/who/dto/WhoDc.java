package com.jeff.mud.command.who.dto;

import java.util.Collection;
import java.util.Set;

import com.jeff.mud.domain.charactor.dto.CharactorDc;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WhoDc {
	private Set<CharactorDc> players;
	
	public WhoDc(Collection<CharactorDc> players) {
		this.players = Set.copyOf(players);
	}
}
