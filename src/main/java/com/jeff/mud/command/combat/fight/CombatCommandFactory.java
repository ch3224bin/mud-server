package com.jeff.mud.command.combat.fight;

import org.springframework.beans.factory.FactoryBean;

import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.skill.constants.SkillAction;
import com.jeff.mud.global.message.CustomMessagingTemplate;

public class CombatCommandFactory implements FactoryBean<WeaponCombatCommand> {
	
	private final CurrentUserManager currentUserManager;
	private final CustomMessagingTemplate customMessagingTemplate;
	private final SkillAction skillAction;
	private final CommandConstants commandConstants;
	
	public CombatCommandFactory(CurrentUserManager currentUserManager, CustomMessagingTemplate customMessagingTemplate,
			SkillAction skillAction, CommandConstants commandConstants) {
		this.currentUserManager = currentUserManager;
		this.customMessagingTemplate = customMessagingTemplate;
		this.skillAction = skillAction;
		this.commandConstants = commandConstants;
	}

	@Override
	public WeaponCombatCommand getObject() throws Exception {
		return new WeaponCombatCommand(currentUserManager, customMessagingTemplate, skillAction, commandConstants);
	}

	@Override
	public Class<?> getObjectType() {
		return WeaponCombatCommand.class;
	}

}
