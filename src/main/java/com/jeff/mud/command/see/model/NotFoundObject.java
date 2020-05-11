package com.jeff.mud.command.see.model;

import com.jeff.mud.template.Template;

import lombok.Getter;

@Getter
public class NotFoundObject implements Seeable {
	
	private final String target;

	public NotFoundObject(String target) {
		this.target = target;
	}

	@Override
	public Template template() {
		return Template.notFoundObject;
	}

}
