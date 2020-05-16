package com.jeff.mud.domain.skill.constants;

public enum Skills implements Skillable {
	fist {
		@Override
		public String getName() {
			return "주먹";
		}

		@Override
		public SkillType getType() {
			return SkillType.melee;
		}

		@Override
		public String getSuccess() {
			return "을(를) 쳤습니다.";
		}

		@Override
		public String getFail() {
			return "주먹이 허공을 가릅니다.";
		}
	},
	kick {
		@Override
		public String getName() {
			return "발차기";
		}

		@Override
		public SkillType getType() {
			return SkillType.melee;
		}

		@Override
		public String getSuccess() {
			return null;
		}

		@Override
		public String getFail() {
			return null;
		}
	},
	headButt {
		@Override
		public String getName() {
			return "박치기";
		}

		@Override
		public SkillType getType() {
			return SkillType.melee;
		}

		@Override
		public String getSuccess() {
			return null;
		}

		@Override
		public String getFail() {
			return null;
		}
	},
	;
}
