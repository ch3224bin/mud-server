package com.jeff.mud.domain.skill.constants;

public enum Skills implements Skillable {
	근접전_격투 {
		@Override
		public String skillName() {
			return "근접전(격투)";
		}
	},
	근접전_도검 {
		@Override
		public String skillName() {
			return "근접전(도검)";
		}
	},
	;
}
