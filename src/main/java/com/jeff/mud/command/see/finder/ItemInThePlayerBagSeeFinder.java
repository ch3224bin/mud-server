package com.jeff.mud.command.see.finder;

import java.util.Optional;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.item.dto.ItemDc;

@Component
@Order(3)
public class ItemInThePlayerBagSeeFinder implements Finder<Seeable> {

	@Override
	public Optional<Seeable> find(CommandDataCarrier input) {
		return input.getPlayer().getCharactorBag().getItem(input.getTarget()).map(ItemDc::new);
	}

}
