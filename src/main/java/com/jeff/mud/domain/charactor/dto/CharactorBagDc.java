package com.jeff.mud.domain.charactor.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.charactor.domain.CharactorBag;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.domain.ItemBroker;
import com.jeff.mud.template.Template;

import lombok.Getter;

@Getter
public class CharactorBagDc implements Seeable {
	
	private long id;
	private List<String> items;
	
	public CharactorBagDc(CharactorBag charactorBag) {
		this.id = charactorBag.getId();
		this.items = charactorBag.getItemBrokers().stream()
			.map(ItemBroker::getItem)
			.map(Item::getName)
			.collect(Collectors.toList());
	}

	@Override
	public Template template() {
		return Template.playerBag;
	}

}
