package com.jeff.mud.command.see.model;

import com.jeff.mud.template.Template;

import lombok.Getter;

@Getter
public class SeeDenyObject implements Seeable {
	
	private final String msg;

	public SeeDenyObject(String msg) {
		this.msg = msg;
	}

	@Override
	public Template template() {
		return Template.seeDenyObject;
	}
}
