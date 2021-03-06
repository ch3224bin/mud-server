package com.jeff.mud.domain.charactor.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.charactor.dao.CharactorRepository;
import com.jeff.mud.domain.charactor.dao.NonPlayerRepository;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.room.domain.Room;

@Component
public class CharactorService {

	private final PlayerRepository playerRepository;
	private final NonPlayerRepository nonPlayerRepository;
	private final CharactorRepository<Charactor> charactorRepository;
	
	public CharactorService(PlayerRepository playerRepository, NonPlayerRepository nonPlayerRepository, CharactorRepository<Charactor> charactorRepository) {
		this.playerRepository = playerRepository;
		this.nonPlayerRepository = nonPlayerRepository;
		this.charactorRepository = charactorRepository;
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void save(Charactor charactor) {
		charactorRepository.save(charactor);
	}
	
	public List<CharactorDc> getCharactorsInTheRoomWithOutMe(CommandDataCarrier input) {
		return getCharactorsInTheRoomWithOutMe(input.getPlayer(), input.getPlayer().getRoom());
	}
	
	public List<CharactorDc> getCharactorsInTheRoomWithOutMe(Player player, Room room) {
		List<Player> players = playerRepository.findByRoomAndOnlineAndNotExistsMe(room, player);
		List<NonPlayer> nonPlayers = nonPlayerRepository.findByRoom(room);
		
		return Stream.concat(players.stream(), nonPlayers.stream())
				.map(CharactorDc::new)
				.collect(Collectors.toList());
	}
}
