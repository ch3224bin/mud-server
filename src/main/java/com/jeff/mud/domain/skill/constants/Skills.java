package com.jeff.mud.domain.skill.constants;

import lombok.Getter;

@Getter
public enum Skills {
	격투 ("근접전(격투)", 25),
	도검 ("근접전(도검)", 20),
	권총 ("사격(권총)", 20),
	기관단총 ("사격(기관단총)", 15),
	기관총 ("사격(기관총)", 10),
	도끼 ("근접전(도끼)", 15),
	도리깨 ("근접전(도리깨)", 10),
	동력톱 ("근접전(동력톱)", 10),
	라이플 ("사격(라이플)", 25),
	산탄총 ("사격(산탄총)", 25),
	중화기 ("사격(중화기)", 10),
	창 ("근접전(창)", 20),
	채찍 ("근접전(채찍)", 5),
	화염방사기 ("사격(화염방사기)", 10),
	활 ("사격(활)", 15),
	가롯테 ("근접전(가롯테)", 15),
	투척 ("투척", 20),
	;
	
	private final String skillName;
	private final int defaultPoint;
	Skills (String skillName, int defaultPoint) {
		this.skillName = skillName;
		this.defaultPoint = defaultPoint;
	}
}
