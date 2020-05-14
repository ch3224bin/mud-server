package com.jeff.mud.domain.charactor.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.charactor.dao.NonPlayerRepository;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;

@Component
public class CharactorService {

	private final PlayerRepository playerRepository;
	private final NonPlayerRepository nonPlayerRepository;
	
	public CharactorService(PlayerRepository playerRepository, NonPlayerRepository nonPlayerRepository) {
		this.playerRepository = playerRepository;
		this.nonPlayerRepository = nonPlayerRepository;
	}
	
	public List<CharactorDc> getCharactorsInTheRoomWithOutMe(CommandDataCarrier input) {
		List<Player> players = playerRepository.findByRoomAndOnlineAndNotExistsMe(input.getPlayer().getRoom(), input.getPlayer());
		List<NonPlayer> nonPlayers = nonPlayerRepository.findByRoom(input.getPlayer().getRoom());
		
		return Stream.concat(players.stream(), nonPlayers.stream())
			.map(CharactorDc::new)
			.collect(Collectors.toList());
	}
}
