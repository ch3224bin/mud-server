package com.jeff.mud.domain.skill.constants;

import com.jeff.mud.domain.item.constants.Weapons;
import com.jeff.mud.domain.item.domain.Weapon;

public enum Skills implements Skillable {
	punch {
		@Override
		public String getName() {
			return "주먹";
		}

		@Override
		public SkillType getType() {
			return SkillType.weapon;
		}

		@Override
		public Weapon defaultWeapon() {
			return Weapons.fist.createWeapon();
		}
	},
	kick {
		@Override
		public String getName() {
			return "발차기";
		}
		
		@Override
		public SkillType getType() {
			return SkillType.notWeapon;
		}
		
		@Override
		public Weapon defaultWeapon() {
			return Weapons.leg.createWeapon();
		}
	},
	headButt {
		@Override
		public String getName() {
			return "박치기";
		}
		
		@Override
		public SkillType getType() {
			return SkillType.notWeapon;
		}
		
		@Override
		public Weapon defaultWeapon() {
			return Weapons.fist.createWeapon();
		}
	},
	stab {
		@Override
		public String getName() {
			return "찔러";
		}
		
		@Override
		public SkillType getType() {
			return SkillType.weapon;
		}
		
		@Override
		public Weapon defaultWeapon() {
			return Weapons.fist.createWeapon();
		}
	}
	;
}
