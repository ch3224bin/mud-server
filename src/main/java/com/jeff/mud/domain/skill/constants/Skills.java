package com.jeff.mud.domain.skill.constants;

import com.jeff.mud.domain.item.constants.Weapons;
import com.jeff.mud.domain.item.domain.Weapon;

public enum Skills implements Skillable {
	// TODO 파생 기술이 있어야한다..
	// 근접전(격투)에는 주먹, 발차기, 밀치기, 붙잡기 등..
	fight_brawl {
		@Override
		public String getName() {
			return "근접전(격투)";
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
