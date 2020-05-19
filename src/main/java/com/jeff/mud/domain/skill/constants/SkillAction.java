package com.jeff.mud.domain.skill.constants;

import com.jeff.mud.domain.item.constants.Weapons;
import com.jeff.mud.domain.item.domain.Weapon;

public enum SkillAction implements SkillActionable {
	PUNCH {
		@Override
		public String actionName() {
			return "주먹";
		}
		
		@Override
		public Skills parentSkill() {
			return Skills.근접전_격투;
		}

		@Override
		public SkillType type() {
			return SkillType.USE_PLAYER_WEAPON;
		}

		@Override
		public Weapon defaultWeapon() {
			return Weapons.FIST.createWeapon();
		}
	},
	KICK {
		@Override
		public String actionName() {
			return "발차기";
		}
		
		@Override
		public Skills parentSkill() {
			return Skills.근접전_격투;
		}

		@Override
		public SkillType type() {
			return SkillType.NOT_USE_PLAYER_WEAPON;
		}

		@Override
		public Weapon defaultWeapon() {
			return Weapons.LEG.createWeapon();
		}
	},
	HEAD_BUTT {
		@Override
		public String actionName() {
			return "박치기";
		}
		
		@Override
		public Skills parentSkill() {
			return Skills.근접전_격투;
		}

		@Override
		public SkillType type() {
			return SkillType.NOT_USE_PLAYER_WEAPON;
		}

		@Override
		public Weapon defaultWeapon() {
			return Weapons.HEAD.createWeapon();
		}
	},
	STAB {
		@Override
		public String actionName() {
			return "찌르기";
		}
		
		@Override
		public Skills parentSkill() {
			return Skills.근접전_도검;
		}
		
		@Override
		public SkillType type() {
			return SkillType.USE_PLAYER_WEAPON;
		}
		
		@Override
		public Weapon defaultWeapon() {
			return Weapons.JACK_KNIFE.createWeapon();
		}
	},
	;
}
