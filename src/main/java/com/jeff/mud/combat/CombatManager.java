package com.jeff.mud.combat;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.template.Template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CombatManager {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final StatusChangeService statusChangeService;
	
	public CombatManager(CustomMessagingTemplate customMessagingTemplate, StatusChangeService statusChangeService) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.statusChangeService = statusChangeService;
	}

	@Async
	@Transactional
	public void startCombat(CharactorDc attacker, CharactorDc defender) {
		log.info("start combat");
		
		// TODO 공격자 수비자 그룹 나누기
		// TODO DEX 순으로 한대씩 친다.
		// TODO 기술은 한라운드에 한번
		// TODO 추가 명령어가 이 쓰레드에 투입되어야 한다.
		
		boolean isFinish = false;
		while (!isFinish) {
			
			
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		
		log.info("end combat");
	}
	
	class CombatZone {
		
	}

}
