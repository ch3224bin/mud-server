package com.jeff.mud.command.combat.fight;

import java.util.Arrays;
import java.util.List;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.who.listener.CurrentUserManager;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.skill.constants.SkillAction;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;

public class WeaponCombatCommand extends CombatCommand {
	
	private final CurrentUserManager currentUserManager;
	private final CustomMessagingTemplate customMessagingTemplate;
	private final SkillAction skillAction;
	private final CommandConstants commandConstants;
	
	public WeaponCombatCommand(CurrentUserManager currentUserManager, CustomMessagingTemplate customMessagingTemplate,
			SkillAction skillAction, CommandConstants commandConstants) {
		this.currentUserManager = currentUserManager;
		this.customMessagingTemplate = customMessagingTemplate;
		this.skillAction = skillAction;
		this.commandConstants = commandConstants;
	}

	@Override
	protected boolean validate(CommandDataCarrier input) {
		// 착용 무기에 해당하는 스킬만 가능 
		Weapon weapon = input.getPlayer().getEquipment().getWeapon();
		if (!getSkillAction().parentSkills().contains(weapon.getType().weaponSkill() )) {
			getMessagingTemplate().sendToYou(input.getUsername(), Template.defaultMessage, "착용한 무기로 " + getSkillAction().actionName() + "을(를) 할 수 없습니다.");
			return false;
		}
		
		return true;
	}
	
	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.COMBAT);
	}

	@Override
	protected CommandConstants commandConstants() {
		return commandConstants;
	}
	
	@Override
	protected SkillAction getSkillAction() {
		return skillAction;
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

	@Override
	protected CurrentUserManager getCurrentUserManager() {
		return currentUserManager;
	}

	@Override
	protected CustomMessagingTemplate getMessagingTemplate() {
		return customMessagingTemplate;
	}
}
