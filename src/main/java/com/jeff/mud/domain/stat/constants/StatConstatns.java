package com.jeff.mud.domain.stat.constants;

public enum StatConstatns implements Statable {
	STR {
		@Override
		public String getName() {
			return "힘";
		}
	},
	CON {
		@Override
		public String getName() {
			return "건강";
		}
	},
	SIZ {
		@Override
		public String getName() {
			return "크기";
		}
	},
	INT {
		@Override
		public String getName() {
			return "지능";
		}
	},
	POW {
		@Override
		public String getName() {
			return "의지력";
		}
	},
	DEX {
		@Override
		public String getName() {
			return "민첩";
		}
	},
	APP {
		@Override
		public String getName() {
			return "외모";
		}
	},
	EDU {
		@Override
		public String getName() {
			return "교육";
		}
	},
}
