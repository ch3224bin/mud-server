package com.jeff.mud.combat;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.jeff.mud.domain.charactor.dao.NonPlayerRepository;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.skill.constants.SkillAction;
import com.jeff.mud.domain.stat.constants.Stats;
import com.jeff.mud.domain.stat.rule.Dice;

import lombok.Getter;

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
	
	public CombatZoneFactory(PlayerRepository playerRepository, NonPlayerRepository nonPlayerRepository) {
		this.playerRepository = playerRepository;
		this.nonPlayerRepository = nonPlayerRepository;
	}
	
	public CombatZone createCombatZone(CharactorDc attacker, CharactorDc defender) {
		Player firstman = playerRepository.findByName(attacker.getName()).get();
		
		Map<String, Player> players = new HashMap<>();
		players.put(firstman.getAccount().getUsername(), firstman);
		
		Set<NonPlayer> npcs = new HashSet<>();
		npcs.add(nonPlayerRepository.findByName(defender.getName()).get());
		
		return new CombatZone(firstman, players, npcs);
	}
	
	@Getter
	public static class CombatZone {
		
		private final String uid; 
		private final Map<String, Player> players;
		private final Set<NonPlayer> npcs;
		private final Player firstman;
		private final List<Charactor> orderedFighters;
		private String currentTurnPlayer;
		private InputSkill inputSkill;
		
		private CombatZone(Player firstman, Map<String, Player> players, Set<NonPlayer> npcs) {
			uid = UUID.randomUUID().toString();
			this.firstman = firstman;
			this.players = players;
			this.npcs = npcs;
			orderedFighters = getOrderedFigthers(players, npcs);
		}
		
		boolean isPlayersAllDown() {
			return isCharactorsAllDown(players.values());
		}
		
		boolean isNpcsAllDown() {
			return isCharactorsAllDown(npcs);
		}
		
		private boolean isCharactorsAllDown(Collection<? extends Charactor> charactors) {
			for (Charactor c : charactors) {
				if (c.getStatus().getHp() > 0) {
					return false;
				}
			}
			return true;
		}
		
		/**
		 * DEX 순으로 공격 순서를 정한다. DEX 큰 놈이 먼저
		 * DEX가 같으면 1d100 주사위 굴려 작은놈이 먼저
		 * 
		 * @param players
		 * @param npcs
		 * @return
		 */
		private List<Charactor> getOrderedFigthers(Map<String, Player> players, Set<NonPlayer> npcs) {
			Stream<Player> ps = players.values().stream();
			Stream<NonPlayer> ns = npcs.stream();
			
			List<Charactor> orderedFighters = Stream.concat(ps, ns)
				.sorted((a, b) -> {
					int aVal = a.getStat(Stats.DEX).getValue();
					int bVal = b.getStat(Stats.DEX).getValue();
					
					// 언젠가는 끝나니라 믿는다..
					while(true) {
						int result = Integer.compare(bVal, aVal); // 처음엔 DEX가 큰거 다음엔 주사위 굴려 작은거
						if (result != 0) {
							return result;
						}
						
						bVal = new Dice(1, 100).getValue(); // player
						aVal = new Dice(1, 100).getValue(); // npc
					}
				})
				.collect(Collectors.toList());
			return orderedFighters;
		}

		public void putCommand(String target, SkillAction action) {
			this.inputSkill = new InputSkill(target, action);
		}
		
		public void clearInputSkill() {
			this.inputSkill = null;
		}
		
		public boolean hasInputSkill() {
			return this.inputSkill != null;
		}
		
		public boolean isMyTurn(String username) {
			return username.equals(currentTurnPlayer);
		}
		
		public void setCurrentTurnPlayer(String currentTurnPlayer) {
			this.currentTurnPlayer = currentTurnPlayer;
		}
	}
}
