package com.jeff.mud.domain.player.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.domain.ItemBroker;
import com.jeff.mud.domain.player.domain.PlayerBag;
import com.jeff.mud.template.Template;

import lombok.Getter;

@Getter
public class PlayerBagDc implements Seeable {
	
	private long id;
	private List<String> items;
	
	public PlayerBagDc(PlayerBag playerBag) {
		this.id = playerBag.getId();
		this.items = playerBag.getItemBrokers().stream()
			.map(ItemBroker::getItem)
			.map(Item::getName)
			.collect(Collectors.toList());
	}

	@Override
	public Template template() {
		return Template.playerBag;
	}

}
