package com.jeff.mud.combat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.mud.domain.charactor.dao.NonPlayerRepository;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.item.constants.Weapons;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.item.service.StatusService;
import com.jeff.mud.domain.skill.constants.Skills;
import com.jeff.mud.domain.skill.domain.Skill.Result;
import com.jeff.mud.domain.stat.constants.Stats;
import com.jeff.mud.domain.stat.rule.DamegeBunusRule;
import com.jeff.mud.domain.stat.rule.Dice;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.template.Template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CombatManager {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final StatusService statusService;
	private final PlayerRepository playerRepository;
	private final NonPlayerRepository nonPlayerRepository;
	
	public CombatManager(CustomMessagingTemplate customMessagingTemplate, StatusService statusService,
			PlayerRepository playerRepository, NonPlayerRepository nonPlayerRepository) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.statusService = statusService;
		this.playerRepository = playerRepository;
		this.nonPlayerRepository = nonPlayerRepository;
	}

	@Async
	@Transactional
	public void startCombat(CharactorDc attacker, CharactorDc defender) {
		log.info("start combat");
		
		// TODO 공격자 수비자 그룹 나누기
		// TODO DEX 순으로 턴이 돈다.
		// TODO 공격자는 공격 기술 입력
		// TODO 추가적인 기술 성공하면 기술 더 입력
		// TODO 추가 명령어가 이 쓰레드에 투입되어야 한다.
		
		Map<String, Player> players = new HashMap<>();
		Set<NonPlayer> npcs = new HashSet<>();
		
		Player firstman = null;
		if (!attacker.isNpc()) {
			Player player = firstman = playerRepository.findByName(attacker.getName()).get();
			players.put(player.getAccount().getUsername(), player);
		}
		
		if (defender.isNpc()) {
			npcs.add(nonPlayerRepository.findByName(defender.getName()).get());
		}
		
		customMessagingTemplate.sendToYou(attacker.getUsername(), Template.defaultMessage, String.format("당신은 %s을(를) 공격했습니다. 전투가 시작됩니다.", defender.getName()));
		
		List<Charactor> orderedFighters = getOrderedFigthers(players, npcs);
		
		boolean isFinish = false;
		while (!isFinish) {
			
			for (Charactor c : orderedFighters) {
				// is My turn?
				if (c instanceof Player) {
					boolean receivedCommand = false;
					
					ExecutorService es = Executors.newCachedThreadPool();

					Player player = (Player) c;
					customMessagingTemplate.sendToYou(player.getAccount().getUsername(), Template.defaultMessage, "당신의 공격차례 입니다. 무엇을 할까요?");
					customMessagingTemplate.sendToRoomWithOutMe(player, Template.defaultMessage, String.format("%s의 공격차례 입니다.", player.getName()));
					
			        FutureTask<String> f = new FutureTask<>(() -> {
			        	for (int i = 0; i < 5; i++) {
			        		//customMessagingTemplate.sendToYou(player.getAccount().getUsername(), Template.defaultMessage, String.format("남은 시간 : %d", (5 - i)));
			        		sleep(1000);
			        	}
			            return "done";
			        });
			        
			        es.execute(f);
			        
			        while (receivedCommand || !f.isDone()) {
			        	sleep(100);
			        }
			        
			        if (!receivedCommand) {
			        	// 일반 공격
			        	// 무기에 따라 공격 방법 정해야함.
			        	// 때리는 상대 고르기
			        	if (c.getEquipment().getWeapon() != null) {
			        		
			        	}
			        	
			        	NonPlayer npc = npcs.iterator().next();
			        	attack(firstman, player, npc);
			        	
			        	if (npc.getStatus().getHp() < 1) {
			        		// 전투종료
			        		customMessagingTemplate.sendToRoom(firstman,
									Template.defaultMessage, String.format("%s가 쓰러졌습니다.", npc.getName()));
			        		customMessagingTemplate.sendToRoom(firstman,
				        			Template.defaultMessage, "전투가 끝났습니다.");
			        		return;
			        	}
			        }
				} else {
					// NPC 공격
					Player player = players.values().iterator().next();
					attack(firstman, c, player);
					
					if (player.getStatus().getHp() < 1) {
						// 전투종료
						customMessagingTemplate.sendToRoom(firstman,
								Template.defaultMessage, String.format("%s가 쓰러졌습니다.", player.getName()));
		        		customMessagingTemplate.sendToRoom(firstman,
			        			Template.defaultMessage, "전투가 끝났습니다.");
		        		return;
		        	}
				}
			}
			
		}
		
		log.info("finish combat");
	}
	
	private void attack(Player player, Charactor attacker, Charactor defender) {
		Weapon fist = Weapons.fist.createWeapon();
    	Result judgment = attacker.getSkill(Skills.fist).judgment(new Dice(1, 100));
    	
    	int demage = 0;
    	if (judgment.isSuccess()) {
    		Dice attackRoll = new Dice(fist.getDiceRule());
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
	
	class CombatZone {
		
	}

}
