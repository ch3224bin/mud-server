package com.jeff.mud.template;

import com.jeff.mud.global.message.Pathable;

import lombok.Getter;

@Getter
public enum Template implements Pathable {
	who ("who"),
	welcome ("welcome"),
	defaultMessage ("default_message"),
	room ("room"),
	notFoundObject ("not_found_object"),
	seeDenyObject ("see_deny_object")
	;
	
	private String path;
	Template(String path) {
		this.path = path;
	}
}
