package com.jeff.mud.domain.item.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.template.Template;

import lombok.Getter;

@Getter
public class ItemDc implements Seeable {
	
	private long id;
	private String name;
	private String description;
	private List<ItemDc> items;
	private boolean isContainer;
	
	public ItemDc(Item item) {
		this.id = item.getId();
		this.name = item.getName();
		this.description = item.getDescription();
	}
	
	public ItemDc(Item item, List<Item> items) {
		this(item);
		this.isContainer = true;
		this.items = items.stream()
				.map(ItemDc::new)
				.collect(Collectors.toList());
	}

	@Override
	public Template template() {
		return Template.item;
	}

}
