package com.jeff.mud.domain.item.constants;

import com.jeff.mud.combat.constants.Judgments;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.skill.constants.Skills;

public enum Weapons implements WeaponDefinition {
	fist {
		@Override
		public Weapon createWeapon() {
			return new Weapon(this, "맨주먹", 1, 3, 0, 50);
		}

		@Override
		public Skills weaponSkill() {
			return Skills.punch;
		}

		@Override
		public String getMessage(Judgments judgments) {
			switch(judgments) {
			case success:
				return "을(를) 쳤습니다.";
			case fail:
				return "주먹이 허공을 가릅니다.";
			case critical:
				return "을(를) 힘껏 쳤습니다.";
			case fumble:
				return "주먹이 엉뚱한 곳을 때립니다. \"으악 내 주먹\"";
			default:
				return "";
			}
		}
	},
	leg {
		@Override
		public Weapon createWeapon() {
			return new Weapon(this, "다리", 1, 4, 0, 30);
		}

		@Override
		public Skills weaponSkill() {
			return Skills.kick;
		}

		@Override
		public String getMessage(Judgments judgments) {
			switch(judgments) {
			case success:
				return "을(를) 찼습니다.";
			case fail:
				return "발차기가 빗나갔습니다.";
			case critical:
				return "을(를) 힘껏 찼습니다.";
			case fumble:
				return "다리로 엉뚱한 곳을 찹니다. \"으악 내 발\"";
			default:
				return "";
			}
		}
	},
	foldingKnife {
		@Override
		public Weapon createWeapon() {
			return new Weapon(this, "접이식 나이프", 1, 4, 0, 25);
		}
		
		@Override
		public Skills weaponSkill() {
			return Skills.stab;
		}
		
		@Override
		public String getMessage(Judgments judgments) {
			return null;
		}
	},
	combatKnife {
		@Override
		public Weapon createWeapon() {
			return new Weapon(this, "군용 나이프", 1, 4, 2, 25);
		}
		
		@Override
		public Skills weaponSkill() {
			return Skills.stab;
		}
		
		@Override
		public String getMessage(Judgments judgments) {
			return null;
		}
	},
	;
}
