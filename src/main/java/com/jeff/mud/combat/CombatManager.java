package com.jeff.mud.combat;

import com.jeff.mud.combat.model.CombatZone;
import com.jeff.mud.combat.model.InputSkill;
import com.jeff.mud.combat.model.Judgment;
import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.charactor.service.CharactorService;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.item.service.StatusService;
import com.jeff.mud.domain.skill.constants.SkillAction;
import com.jeff.mud.domain.skill.constants.SkillType;
import com.jeff.mud.domain.skill.domain.Skill;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.CombatTempleteDc;
import com.jeff.mud.template.Template;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CombatManager {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final CombatMessageHandler combatMessageHandler;
	private final StatusService statusService;
	private final CharactorService charactorService;
	private final CombatZoneFactory combatZoneFactory;
	
	public CombatManager(CustomMessagingTemplate customMessagingTemplate, StatusService statusService,
			CombatMessageHandler combatMessageHandler, CharactorService charactorService,
			CombatZoneFactory combatZoneFactory) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.combatMessageHandler = combatMessageHandler;
		this.statusService = statusService;
		this.charactorService = charactorService;
		this.combatZoneFactory = combatZoneFactory;
	}
	
	@Async
	@Transactional
	public void startCombat(CharactorDc attacker, CharactorDc defender) {
		CombatZone combatZone = combatZoneFactory.createCombatZone(attacker, defender);
		Player firstman = combatZone.getFirstman();
		List<Charactor> orderedFighters = combatZone.getOrderedFighters();
		
		CombatTempleteDc combatStartTempleteDc = new CombatTempleteDc(attacker.getName(), defender.getName());
		customMessagingTemplate.sendToYou(attacker.getUsername(), Template.combatStartSendMe, combatStartTempleteDc);
		customMessagingTemplate.sendToRoomWithOutMe(firstman, Template.combatStartSendRoom, combatStartTempleteDc);
		customMessagingTemplate.sendToRoom(firstman, Template.defaultMessage, "전투가 시작됐습니다.");
		
		for (Charactor c : orderedFighters) {
			c.changeState(CharactorState.COMBAT);
			charactorService.save(c); // db에 flush 하기 위해
		}
		
		boolean isFinish = false;
		while (!isFinish) {
			for (Charactor c : orderedFighters) {
				if (c instanceof Player) {
		        	if (isFinish = doPlayerTurn(combatZone, c)) {
		        		break;
		        	}
		        	sleep(1000);
				} else {
					if (isFinish = doNonPlayerTurn(combatZone, c)) {
		        		break;
		        	}
					sleep(1000);
				}
				
				// TODO attack() 메소드 안의 skill 입력되어있는지 체크하는 부분과 섞였다.
				combatZone.clearInputSkill();
			}
		}
		
		customMessagingTemplate.sendToRoom(firstman, Template.defaultMessage, "전투가 끝났습니다.");
		
		for (Charactor c : orderedFighters) {
			// return 되면서 commit 되기 때문에 service를 사용하지 않는다.
			if (c.getState() == CharactorState.COMBAT) {
				c.changeState(CharactorState.NORMAL);
			}
		}
		
		combatZone.finish();
	}

	private boolean doNonPlayerTurn(CombatZone combatZone, Charactor charactor) {
		combatZone.setCurrentTurnPlayer(charactor.getName());
		Player player = combatZone.getPlayers().values().iterator().next();
		Weapon weapon = charactor.getEquipment().getWeapon();
		attack(combatZone.getFirstman(), charactor, player, weapon);
		
		if (player.getStatus().getHp() < 1) {
			CombatTempleteDc combatTempleteDc = new CombatTempleteDc(charactor.getName(), player.getName());
			customMessagingTemplate.sendToYou(player.getAccount().getUsername(), Template.defaultMessage, "당신은 쓰러졌습니다.");
			customMessagingTemplate.sendToRoomWithOutMe(player, Template.combatEnemyDownSendAll, combatTempleteDc);
		}
		
		return combatZone.isPlayersAllDown();
	}

	private boolean doPlayerTurn(CombatZone combatZone, Charactor charactor) {
		boolean receivedCommand = false;

		Player player = (Player) charactor;
		NonPlayer npc = combatZone.getNpcs().iterator().next();
		String username = player.getAccount().getUsername();
		combatZone.setCurrentTurnPlayer(username);
		CombatTempleteDc combatTempleteDc = new CombatTempleteDc(player.getName(), npc.getName());
		customMessagingTemplate.sendToYou(username, Template.defaultMessage, "당신의 차례 입니다. 무엇을 할까요?");
		customMessagingTemplate.sendToRoomWithOutMe(player, Template.combatTurnSendRoom, combatTempleteDc);
		
		for (int i = 0; i < 100 && !receivedCommand; i++) {
			receivedCommand = combatZone.hasInputSkill();
			if (!receivedCommand) {
				sleep(50);
			}
		}
		
		Weapon weapon = charactor.getEquipment().getWeapon();
		if (receivedCommand) {
			InputSkill inputSkill = combatZone.getInputSkill();
			SkillAction action = inputSkill.getAction();
			if (action.type() == SkillType.DONT_USE_PLAYER_WEAPON) {
				weapon = action.defaultWeapon();
			}
		}
		
		attack(combatZone.getFirstman(), player, npc, weapon);
		
		if (npc.getStatus().getHp() < 1) {
			customMessagingTemplate.sendToRoom(combatZone.getFirstman(), Template.combatEnemyDownSendAll, combatTempleteDc);
		}
		
		return combatZone.isNpcsAllDown();
	}
	
	private void attack(Player player, Charactor attacker, Charactor defender, Weapon weapon) {
    	Skill playerSkill = attacker.getSkill(weapon.getType().weaponSkill());
    	Judgment judgment = new Judgment(playerSkill, weapon);
    	String message = weapon.getType().getMessage(judgment.getJudgments());
    	
    	int demage = judgment.getJudgments().getDamege(attacker, weapon);
    	combatMessageHandler.sendCombatMessage(judgment.getJudgments(), attacker, defender, message, demage);
    	
    	// TODO extreme시 스킬 향상 기회
    	// TODO 사용 액션 횟수 카운팅. 액션 + 스킬 숙련도 체크하여 새로운 스킬 등장
    	
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
}
