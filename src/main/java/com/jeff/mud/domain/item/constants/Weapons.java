package com.jeff.mud.domain.item.constants;

import com.jeff.mud.combat.constants.Judgments;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.skill.constants.Skills;

public enum Weapons implements WeaponDefinition {
	fist {
		@Override
		public Weapon createWeapon() {
			return Weapon.builder()
			.name("맨주먹").count(1).sided(3).bonus(0).accuracy(50)
			.type(this)
			.build();
		}

		@Override
		public Skills weaponSkill() {
			return Skills.fight_brawl;
		}

		@Override
		public String getMessage(Judgments judgments) {
			switch(judgments) {
			case success:
				return "을(를) 쳤습니다.";
			case fail:
				return "의 주먹이 허공을 가릅니다.";
			case critical:
				return "의 급소를 정확히 때립니다. 빠셍~";
			case fumble:
				return "의 주먹이 엉뚱한 곳을 때립니다. \"으악 내 주먹\"";
			default:
				return "";
			}
		}
	},
	leg {
		@Override
		public Weapon createWeapon() {
			return Weapon.builder()
					.name("다리").count(1).sided(4).bonus(0).accuracy(30)
					.type(this)
					.build();
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
				return "의 발차기가 빗나갔습니다.";
			case critical:
				return "을(를) 엄청난 스피드로 정도로 찹니다. 무영각~";
			case fumble:
				return "은(는) 엉뚱한 곳을 찹니다. \"으악 내 발\"";
			default:
				return "";
			}
		}
	},
	foldingKnife {
		@Override
		public Weapon createWeapon() {
			return Weapon.builder()
					.name("접이식 나이프").count(1).sided(4).bonus(0).accuracy(25)
					.type(this).isGetable(true).grade(ItemGrade.common)
					.description("짧지만 날카로운 접이식 칼이다.")
					.build();
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
			return Weapon.builder()
					.name("군용 나이프").count(1).sided(4).bonus(2).accuracy(25)
					.type(this).isGetable(true).grade(ItemGrade.uncommon)
					.build();
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
