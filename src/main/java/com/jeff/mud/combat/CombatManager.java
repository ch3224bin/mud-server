package com.jeff.mud.combat;

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
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.charactor.dao.NonPlayerRepository;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.charactor.service.CharactorService;
import com.jeff.mud.domain.item.constants.Weapons;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.item.service.StatusService;
import com.jeff.mud.domain.skill.constants.Skills;
import com.jeff.mud.domain.skill.domain.Skill.Result;
import com.jeff.mud.domain.stat.constants.Stats;
import com.jeff.mud.domain.stat.rule.DamegeBunusRule;
import com.jeff.mud.domain.stat.rule.Dice;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CombatManager {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final StatusService statusService;
	private final CharactorService charactorService;
	private final PlayerRepository playerRepository;
	private final NonPlayerRepository nonPlayerRepository;
	private final CurrentUserManager currentUserManager;
//	private static final Map<String, CombatZone> combatZones = new ConcurrentHashMap<>();
	
	public CombatManager(CustomMessagingTemplate customMessagingTemplate, StatusService statusService,
			CharactorService charactorService,
			PlayerRepository playerRepository, NonPlayerRepository nonPlayerRepository,
			CurrentUserManager currentUserManager) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.statusService = statusService;
		this.charactorService = charactorService;
		this.playerRepository = playerRepository;
		this.nonPlayerRepository = nonPlayerRepository;
		this.currentUserManager = currentUserManager;
	}
	
//	public static CombatZone getCombatZone(String uid) {
//		return combatZones.get(uid);
//	}

	@Async
	@Transactional
	public void startCombat(CharactorDc attacker, CharactorDc defender) {
		log.info("start combat");
		
		// TODO 공격자 수비자 그룹 나누기
		// TODO DEX 순으로 턴이 돈다.
		// TODO 공격자는 공격 기술 입력
		// TODO 추가적인 기술 성공하면 기술 더 입력
		// TODO 추가 명령어가 이 쓰레드에 투입되어야 한다.
		
		CombatZone combatZone = new CombatZone(attacker, defender);
//		combatZones.put(combatZone.getUid(), combatZone);
		CharactorDc sessionPlayer = currentUserManager.getPlayer(combatZone.getFirstman().getAccount().getUsername());
		sessionPlayer.setCombatZone(combatZone);
		
		Player firstman = combatZone.getFirstman();
		Map<String, Player> players = combatZone.getPlayers();
		Set<NonPlayer> npcs = combatZone.getNpcs();
		List<Charactor> orderedFighters = combatZone.getOrderedFighters();
		
		customMessagingTemplate.sendToYou(attacker.getUsername(), Template.defaultMessage, String.format("당신은 %s을(를) 공격했습니다. 전투가 시작됩니다.", defender.getName()));
		for (Charactor c : orderedFighters) {
			c.changeState(CharactorState.combat);
			charactorService.save(c); // db에 flush 하기 위해
		}
		
		boolean isFinish = false;
		while (!isFinish) {
			
			for (Charactor c : orderedFighters) {
				// is My turn?
				if (c instanceof Player) {
					boolean receivedCommand = false;

					Player player = (Player) c;
					customMessagingTemplate.sendToYou(player.getAccount().getUsername(), Template.defaultMessage, "당신의 공격차례 입니다. 무엇을 할까요?");
					customMessagingTemplate.sendToRoomWithOutMe(player, Template.defaultMessage, String.format("%s의 공격차례 입니다.", player.getName()));
			        
		        	for (int i = 0; i < 50 && !receivedCommand; i++) {
		        		//customMessagingTemplate.sendToYou(player.getAccount().getUsername(), Template.defaultMessage, String.format("남은 시간 : %d", (5 - i)));
		        		receivedCommand = combatZone.hasInputSkill();
		        		if (!receivedCommand) {
		        			sleep(50);
		        		}
		        	}
			        
		        	Weapon weapon = Weapons.fist.createWeapon();
			        if (!receivedCommand) {
			        	// 일반 공격
			        	// 무기에 따라 공격 방법 정해야함.
			        	// 때리는 상대 고르기
			        	if (c.getEquipment().getWeapon() != null) {
			        		
			        	} else {
			        		weapon = Weapons.fist.createWeapon();
			        	}
			        } else {
			        	InputSkill inputSkill = combatZone.getInputSkill();
			        	Skills skills = inputSkill.getSkills();
			        	// TODO 스킬과 웨폰을 믹스 해야함
			        	combatZone.clearInputSkill();
			        	weapon = Weapons.fist.createWeapon();
			        }
			        
		        	NonPlayer npc = npcs.iterator().next();
		        	attack(firstman, player, npc, weapon);
		        	
		        	if (npc.getStatus().getHp() < 1) {
		        		// 전투종료
		        		customMessagingTemplate.sendToRoom(firstman,
								Template.defaultMessage, String.format("%s가 쓰러졌습니다.", npc.getName()));
		        		customMessagingTemplate.sendToRoom(firstman,
			        			Template.defaultMessage, "전투가 끝났습니다.");
		        		isFinish = true;
		        		break;
		        	}
		        	sleep(1000);
				} else {
					// NPC 공격
					Player player = players.values().iterator().next();
					attack(firstman, c, player, Weapons.fist.createWeapon());
					
					if (player.getStatus().getHp() < 1) {
						// 전투종료
						customMessagingTemplate.sendToRoom(firstman,
								Template.defaultMessage, String.format("%s가 쓰러졌습니다.", player.getName()));
		        		customMessagingTemplate.sendToRoom(firstman,
			        			Template.defaultMessage, "전투가 끝났습니다.");
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
		
//		combatZones.remove(combatZone.getUid());
		sessionPlayer.setCombatZone(null);
		log.info("finish combat");
	}
	
	private void attack(Player player, Charactor attacker, Charactor defender, Weapon weapon) {
    	Result judgment = attacker.getSkill(Skills.fist).judgment(new Dice(1, 100));
    	
    	int demage = 0;
    	if (judgment.isSuccess()) {
    		Dice attackRoll = new Dice(weapon.getDiceRule());
        	Dice dbRoll = DamegeBunusRule.getDamegeBonusDice(attacker);
        	
        	demage = attackRoll.getValue() + dbRoll.getValue();
        	
        	if (attacker instanceof Player) {
        		customMessagingTemplate.sendToYou(((Player)attacker).getAccount().getUsername(),
        				Template.defaultMessage,
        				String.format("당신은 %s%s %d의 피해", defender.getName(), judgment.getMessage(), demage));
        		customMessagingTemplate.sendToRoomWithOutMe(player,
        				Template.defaultMessage,
        				String.format("%s은(는) %s%s %d의 피해", attacker.getName(), defender.getName(), judgment.getMessage(), demage));
        	} else {
        		customMessagingTemplate.sendToRoom(player,
        				Template.defaultMessage,
        				String.format("%s은(는) %s%s %d의 피해", attacker.getName(), defender.getName(), judgment.getMessage(), demage));
        	}
    	} else {
    		if (attacker instanceof Player) {
	    		customMessagingTemplate.sendToYou(((Player)attacker).getAccount().getUsername(),
	        			Template.defaultMessage,
	        			String.format("당신의 %s", judgment.getMessage()));
	    		customMessagingTemplate.sendToRoomWithOutMe(player,
	    				Template.defaultMessage,
	    				String.format("%s의 %s", attacker.getName(), judgment.getMessage()));
    		} else {
    			customMessagingTemplate.sendToRoom(player,
        				Template.defaultMessage,
        				String.format("%s의 %s", attacker.getName(), judgment.getMessage()));
    		}
    	}
    	
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

}
