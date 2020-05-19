package com.jeff.mud.domain.skill.constants;

import java.util.Set;

import com.google.common.collect.Sets;
import com.jeff.mud.domain.item.constants.Weapons;
import com.jeff.mud.domain.item.domain.Weapon;

public enum SkillAction implements SkillActionable {
	PUNCH {
		@Override
		public String actionName() {
			return "주먹";
		}
		
		@Override
		public Set<Skills> parentSkills() {
			return Sets.newHashSet(Skills.격투);
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
		public Set<Skills> parentSkills() {
			return Sets.newHashSet(Skills.격투);
		}

		@Override
		public SkillType type() {
			return SkillType.DONT_USE_PLAYER_WEAPON;
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
		public Set<Skills> parentSkills() {
			return Sets.newHashSet(Skills.격투);
		}

		@Override
		public SkillType type() {
			return SkillType.DONT_USE_PLAYER_WEAPON;
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
		public Set<Skills> parentSkills() {
			return Sets.newHashSet(Skills.격투, Skills.도검);
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
	SHOOT {
		@Override
		public String actionName() {
			return "쏴";
		}
		
		@Override
		public Set<Skills> parentSkills() {
			return Sets.newHashSet(Skills.격투, Skills.도검);
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
