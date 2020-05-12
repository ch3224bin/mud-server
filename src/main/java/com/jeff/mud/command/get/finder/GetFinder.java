package com.jeff.mud.command.get.finder;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.domain.item.domain.Item;

@Component
public class GetFinder {
	
	private final List<Finder<Item>> finders;
	
	public GetFinder(List<Finder<Item>> finders) {
		this.finders = finders;
	}
	
	public Item findTarget(CommandDataCarrier input) {
		if (!input.hasTarget()) {
			return null;
		}
		
		for (Finder<Item> finder : finders) {
			Optional<Item> find = finder.find(input);
			if (find.isPresent()) {
				return find.get();
			}
		}
		
		return null;
	}
}
