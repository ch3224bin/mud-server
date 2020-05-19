package com.jeff.mud.domain.item.constants;

import com.jeff.mud.combat.constants.Judgments;
import com.jeff.mud.domain.item.domain.Weapon;
import com.jeff.mud.domain.skill.constants.Skills;

public enum Weapons implements WeaponDefinition {
	FIST {
		@Override
		public Weapon createWeapon() {
			return Weapon.builder()
			.name("맨주먹").count(1).sided(3).bonus(0).accuracy(50)
			.type(this)
			.build();
		}

		@Override
		public Skills weaponSkill() {
			return Skills.근접전_격투;
		}

		@Override
		public String getMessage(Judgments judgments) {
			switch(judgments) {
			case SUCCESS:
				return "을(를) 쳤습니다.";
			case FAIL:
				return "의 주먹이 허공을 가릅니다.";
			case EXTREME:
			case CRITICAL:
				return "의 급소를 정확히 때립니다. 빠셍~";
			case FUMBLE:
				return "의 주먹이 엉뚱한 곳을 때립니다. \"으악 내 주먹\"";
			default:
				return "";
			}
		}
	},
	LEG {
		@Override
		public Weapon createWeapon() {
			return Weapon.builder()
					.name("다리").count(1).sided(4).bonus(0).accuracy(30)
					.type(this)
					.build();
		}

		@Override
		public Skills weaponSkill() {
			return Skills.근접전_격투;
		}

		@Override
		public String getMessage(Judgments judgments) {
			switch(judgments) {
			case SUCCESS:
				return "을(를) 찼습니다.";
			case FAIL:
				return "의 발차기가 빗나갔습니다.";
			case EXTREME:
				return "을(를) 엄청난 스피드로 정도로 찹니다. 무영각~";
			case FUMBLE:
				return "은(는) 엉뚱한 곳을 찹니다. \"으악 내 발\"";
			default:
				return "";
			}
		}
	},
	HEAD {
		@Override
		public Weapon createWeapon() {
			return Weapon.builder()
					.name("머리").count(1).sided(4).bonus(0).accuracy(30)
					.type(this)
					.build();
		}
		
		@Override
		public Skills weaponSkill() {
			return Skills.근접전_격투;
		}
		
		@Override
		public String getMessage(Judgments judgments) {
			switch(judgments) {
			case SUCCESS:
				return "을(를) 머리로 박았습니다.";
			case FAIL:
				return "을(를) 맞추지 못했습니다.";
			case EXTREME:
				return "의 머리를 눈이 튀어나올정도로 박습니다! ";
			case FUMBLE:
				return "의 머리는 잘못된 곳을 향해 내달립니다. \"머리인가 망치인가\"";
			default:
				return "";
			}
		}
	},
	JACK_KNIFE {
		@Override
		public Weapon createWeapon() {
			return Weapon.builder()
					.name("잭나이프").count(1).sided(4).bonus(0).accuracy(25)
					.type(this).isGetable(true).grade(ItemGrade.COMMON)
					.description("짧지만 날카로운 접이식 칼이다.")
					.build();
		}
		
		@Override
		public Skills weaponSkill() {
			return Skills.근접전_도검;
		}
		
		@Override
		public String getMessage(Judgments judgments) {
			return null;
		}
	},
	COMBAT_KNIFE {
		@Override
		public Weapon createWeapon() {
			return Weapon.builder()
					.name("군용 나이프").count(1).sided(4).bonus(2).accuracy(25)
					.type(this).isGetable(true).grade(ItemGrade.UNCOMMON)
					.build();
		}
		
		@Override
		public Skills weaponSkill() {
			return Skills.근접전_도검;
		}
		
		@Override
		public String getMessage(Judgments judgments) {
			return null;
		}
	},
	;
}
