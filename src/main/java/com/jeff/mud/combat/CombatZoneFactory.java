package com.jeff.mud.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.jeff.mud.combat.model.CombatZone;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.charactor.dao.NonPlayerRepository;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;

/**
 * 공격자, 방어자 그룹을 나눈다.
 * 공격자, 방어자에 속한 플레이어의 명령어는 CombatZone으로 들어온다.
 *
 * @author ChangHo Vin
 */
@Component
public class CombatZoneFactory {
	
	private final PlayerRepository playerRepository;
	private final NonPlayerRepository nonPlayerRepository;
	private final CurrentUserManager currentUserManager;
	
	public CombatZoneFactory(PlayerRepository playerRepository, NonPlayerRepository nonPlayerRepository,
			CurrentUserManager currentUserManager) {
		this.playerRepository = playerRepository;
		this.nonPlayerRepository = nonPlayerRepository;
		this.currentUserManager = currentUserManager;
	}
	
	public CombatZone createCombatZone(CharactorDc attacker, CharactorDc defender) {
		Player firstman = playerRepository.findByName(attacker.getName()).get();
		
		Map<String, Player> players = new HashMap<>();
		players.put(firstman.getAccount().getUsername(), firstman);
		
		Set<NonPlayer> npcs = new HashSet<>();
		npcs.add(nonPlayerRepository.findByName(defender.getName()).get());
		
		List<CharactorDc> sessionPlayers = new ArrayList<>();
		for (Player p : players.values()) {
			sessionPlayers.add(currentUserManager.getPlayer(p.getAccount().getUsername()));
		}
		
		return new CombatZone(firstman, players, npcs, sessionPlayers);
	}
}
