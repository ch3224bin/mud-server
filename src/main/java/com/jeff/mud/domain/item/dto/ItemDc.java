package com.jeff.mud.domain.item.dto;

import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.template.Template;

import lombok.Getter;

@Getter
public class ItemDc implements Seeable {
	
	private long id;
	private String name;
	private String description;
	
	public ItemDc(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.description = item.getDescription();
	}

	@Override
	public Template template() {
		return Template.item;
	}

}
