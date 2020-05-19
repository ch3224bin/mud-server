package com.jeff.mud.combat.model;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.skill.constants.SkillAction;
import com.jeff.mud.domain.stat.constants.Stats;
import com.jeff.mud.domain.stat.rule.Dice;

import lombok.Getter;

@Getter
public class CombatZone {
	private final String uid; 
	private final Map<String, Player> players;
	private final Set<NonPlayer> npcs;
	private final Player firstman;
	private final List<Charactor> orderedFighters;
	private final List<CharactorDc> sessionPlayers;
	private String currentTurnPlayer;
	private InputSkill inputSkill;
	
	public CombatZone(Player firstman, Map<String, Player> players, Set<NonPlayer> npcs, List<CharactorDc> sessionPlayers) {
		this.uid = UUID.randomUUID().toString();
		this.firstman = firstman;
		this.players = players;
		this.npcs = npcs;
		this.sessionPlayers = sessionPlayers;
		this.orderedFighters = getOrderedFigthers(players, npcs);
		
		// 생성된 CombatZone을 각 플레이어에게 할당
		setCombatZoneToPlayer();
	}
	
	private void setCombatZoneToPlayer() {
		for (CharactorDc c : sessionPlayers) {
			c.setCombatZone(this);
		}
	}
	
	public boolean isPlayersAllDown() {
		return isCharactorsAllDown(players.values());
	}
	
	public boolean isNpcsAllDown() {
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
			.sorted(combatOrderComparator())
			.collect(Collectors.toList());
		return orderedFighters;
	}

	private Comparator<? super Charactor> combatOrderComparator() {
		return (a, b) -> {
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
		};
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
	
	/**
	 * 전투 종료시 호출하여 각 플레이어에 할당된 CombatZone 해제
	 */
	// TODO 어떻게 강제 시킬까?
	public void finish() {
		for (CharactorDc c : sessionPlayers) {
			c.setCombatZone(null);
		}
	}
}
