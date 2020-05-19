package com.jeff.mud.command.combat.fight;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.skill.constants.SkillAction;
import com.jeff.mud.global.message.CustomMessagingTemplate;

@Configuration
public class CombatFactoryBeanAppConfig {
	
	private final CurrentUserManager currentUserManager;
	private final CustomMessagingTemplate customMessagingTemplate;
	
	public CombatFactoryBeanAppConfig(CurrentUserManager currentUserManager, CustomMessagingTemplate customMessagingTemplate) {
		this.currentUserManager = currentUserManager;
		this.customMessagingTemplate = customMessagingTemplate;
	}

	/**
	 * @return 주먹
	 */
	@Bean
	public CombatCommandFactory punchCommand() {
		return new CombatCommandFactory(currentUserManager, customMessagingTemplate,
				SkillAction.PUNCH,
				CommandConstants.PUNCH);
	}
	
	/**
	 * @return 발차기
	 */
	@Bean
	public CombatCommandFactory kickCommand() {
		return new CombatCommandFactory(currentUserManager, customMessagingTemplate,
				SkillAction.KICK,
				CommandConstants.KICK);
	}
	
	/**
	 * @return 박치기
	 */
	@Bean
	public CombatCommandFactory headButtCommand() {
		return new CombatCommandFactory(currentUserManager, customMessagingTemplate,
				SkillAction.HEAD_BUTT,
				CommandConstants.HEAD_BUTT);
	}
}
