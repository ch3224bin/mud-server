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

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.jeff.mud.combat.constants.Judgments;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.charactor.dao.NonPlayerRepository;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.charactor.service.CharactorService;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.item.service.StatusService;
import com.jeff.mud.domain.skill.constants.SkillType;
import com.jeff.mud.domain.skill.constants.Skills;
import com.jeff.mud.domain.skill.domain.Skill;
import com.jeff.mud.domain.stat.constants.Stats;
import com.jeff.mud.domain.stat.rule.DamegeBunusRule;
import com.jeff.mud.domain.stat.rule.Dice;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.CombatTempleteDc;
import com.jeff.mud.template.Template;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CombatManager {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final CombatMessageHandler combatMessageHandler;
	private final StatusService statusService;
	private final CharactorService charactorService;
	private final PlayerRepository playerRepository;
	private final NonPlayerRepository nonPlayerRepository;
	private final CurrentUserManager currentUserManager;
	
	public CombatManager(CustomMessagingTemplate customMessagingTemplate, StatusService statusService,
			CombatMessageHandler combatMessageHandler, CharactorService charactorService,
			PlayerRepository playerRepository, NonPlayerRepository nonPlayerRepository,
			CurrentUserManager currentUserManager) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.combatMessageHandler = combatMessageHandler;
		this.statusService = statusService;
		this.charactorService = charactorService;
		this.playerRepository = playerRepository;
		this.nonPlayerRepository = nonPlayerRepository;
		this.currentUserManager = currentUserManager;
	}
	
	@Async
	@Transactional
	public void startCombat(CharactorDc attacker, CharactorDc defender) {
		log.info("start combat");
		
		// TODO 공격자 수비자 그룹 나누기
		// TODO 공격자는 공격 기술 입력
		// TODO 추가적인 기술 성공하면 기술 더 입력
		// TODO 기술을 사용하면 일정확률로 기술 상승
		
		CombatZone combatZone = new CombatZone(attacker, defender);
		CharactorDc sessionPlayer = currentUserManager.getPlayer(combatZone.getFirstman().getAccount().getUsername());
		sessionPlayer.setCombatZone(combatZone);
		
		Player firstman = combatZone.getFirstman();
		List<Charactor> orderedFighters = combatZone.getOrderedFighters();
		
		CombatTempleteDc combatStartTempleteDc = new CombatTempleteDc(attacker.getName(), defender.getName());
		customMessagingTemplate.sendToYou(attacker.getUsername(), Template.combatStartSendMe, combatStartTempleteDc);
		customMessagingTemplate.sendToRoomWithOutMe(firstman, Template.combatStartSendRoom, combatStartTempleteDc);
		
		for (Charactor c : orderedFighters) {
			c.changeState(CharactorState.combat);
			charactorService.save(c); // db에 flush 하기 위해
		}
		
		boolean isFinish = false;
		while (!isFinish) {
			
			for (Charactor c : orderedFighters) {
				// is My turn?
				if (c instanceof Player) {
					turnPlayer(combatZone, c);
		        	
		        	// 전투종료
		        	if (combatZone.isNpcsAllDown()) {
		        		customMessagingTemplate.sendToRoom(firstman, Template.defaultMessage, "전투가 끝났습니다.");
		        		isFinish = true;
		        		break;
		        	}
		        	sleep(1000);
				} else {
					// NPC 공격
					turnNonPlayer(combatZone, c);
					
					// 전투종료, 플레이어가 졌을때
					if (combatZone.isPlayersAllDown()) {
						customMessagingTemplate.sendToRoom(firstman, Template.defaultMessage, "전투가 끝났습니다.");
		        		isFinish = true;
		        		break;
		        	}
					sleep(1000);
				}
			}
			
		}
		
		for (Charactor c : orderedFighters) {
			// return 되면서 commit 되기 때문에 service를 사용하지 않는다.
			if (c.getState() == CharactorState.combat) {
				c.changeState(CharactorState.normal);
			}
		}
		
		sessionPlayer.setCombatZone(null);
		log.info("finish combat");
	}

	private void turnNonPlayer(CombatZone combatZone, Charactor charactor) {
		combatZone.setCurrentTurnPlayer(charactor.getName());
		Player player = combatZone.getPlayers().values().iterator().next();
		Weapon weapon = charactor.getEquipment().getWeapon();
		attack(combatZone.getFirstman(), charactor, player, weapon);
		
		if (player.getStatus().getHp() < 1) {
			CombatTempleteDc combatTempleteDc = new CombatTempleteDc(charactor.getName(), player.getName());
			customMessagingTemplate.sendToYou(player.getAccount().getUsername(), Template.defaultMessage, "당신은 쓰러졌습니다.");
			customMessagingTemplate.sendToRoomWithOutMe(player, Template.combatEnemyDownSendAll, combatTempleteDc);
		}
	}

	private void turnPlayer(CombatZone combatZone, Charactor charactor) {
		boolean receivedCommand = false;

		Player player = (Player) charactor;
		NonPlayer npc = combatZone.getNpcs().iterator().next();
		String username = player.getAccount().getUsername();
		combatZone.setCurrentTurnPlayer(username);
		CombatTempleteDc combatTempleteDc = new CombatTempleteDc(player.getName(), npc.getName());
		customMessagingTemplate.sendToYou(username, Template.defaultMessage, "당신의 차례 입니다. 무엇을 할까요?");
		customMessagingTemplate.sendToRoomWithOutMe(player, Template.combatTurnSendRoom, combatTempleteDc);
		
		for (int i = 0; i < 50 && !receivedCommand; i++) {
			receivedCommand = combatZone.hasInputSkill();
			if (!receivedCommand) {
				sleep(50);
			}
		}
		
		Weapon weapon = charactor.getEquipment().getWeapon();
		if (receivedCommand) {
			InputSkill inputSkill = combatZone.getInputSkill();
			Skills skills = inputSkill.getSkills();
			if (skills.getType() == SkillType.notWeapon) {
				weapon = skills.defaultWeapon();
			}
			combatZone.clearInputSkill();
		}
		
		attack(combatZone.getFirstman(), player, npc, weapon);
		
		if (npc.getStatus().getHp() < 1) {
			customMessagingTemplate.sendToRoom(combatZone.getFirstman(), Template.combatEnemyDownSendAll, combatTempleteDc);
		}
	}
	
	private void attack(Player player, Charactor attacker, Charactor defender, Weapon weapon) {
    	Skill playerSkill = attacker.getSkill(weapon.getType().weaponSkill());
    	Judgment judgment = new Judgment(playerSkill, weapon);
    	String message = weapon.getType().getMessage(judgment.judgments);
    	
    	int demage = 0;
    	if (judgment.judgments == Judgments.success || judgment.judgments == Judgments.critical) {
    		Dice attackRoll = new Dice(weapon.getDiceRule());
        	Dice dbRoll = DamegeBunusRule.getDamegeBonusDice(attacker);
        	
        	demage = attackRoll.getValue() + weapon.getBonus() + dbRoll.getValue();
    	}
    	combatMessageHandler.sendCombatMessage(judgment.judgments, attacker, defender, message, demage);
    	
    	defender.getStatus().decreaseHp(demage);
    	statusService.save(defender.getStatus());
	}

	private void sleep(int mills) {
		try {
			Thread.sleep(mills);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	@Getter
	public class CombatZone {
		
		final String uid; 
		final Map<String, Player> players = new HashMap<>();
		final Set<NonPlayer> npcs = new HashSet<>();
		Player firstman;
		List<Charactor> orderedFighters;
		String currentTurnPlayer;
		InputSkill inputSkill;
		
		CombatZone(CharactorDc attacker, CharactorDc defender) {
			uid = UUID.randomUUID().toString();
			if (!attacker.isNpc()) {
				Player player = firstman = playerRepository.findByName(attacker.getName()).get();
				players.put(player.getAccount().getUsername(), player);
			}
			
			if (defender.isNpc()) {
				npcs.add(nonPlayerRepository.findByName(defender.getName()).get());
			}
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

		public void putCommand(String target, Skills skill) {
			this.inputSkill = new InputSkill(target, skill);
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
	
	@Getter
	class InputSkill {
		String target;
		Skills skills;
		
		public InputSkill(String target, Skills skills) {
			this.target = target;
			this.skills = skills;
		}
		
		public boolean hasTarget() {
			return Strings.isNullOrEmpty(target);
		}
		
	}
	
	class Judgment {
		int point;
		Dice dice;
		Judgments judgments;
		
		Judgment (Skill playerSkill, Weapon weapon) {
			point = weapon.getAccuracy();
			dice = new Dice(1, weapon.getAccuracy());
	    	if (playerSkill != null) {
	    		point = playerSkill.getPoint();
	    		dice = new Dice(1, playerSkill.getPoint());
	    	}
	    	int value = dice.getValue();
			if (value == 1) {
	    		judgments = Judgments.critical;
	    	} else if (value <= point) {
	    		judgments = Judgments.success;
	    	} else if ((point < 50 && value >= 96 && value <= 100) ||
	    			value == 100) {
	    		judgments = Judgments.fumble;
	    	} else {
	    		judgments = Judgments.fail;
	    	}
		}
	}

}
