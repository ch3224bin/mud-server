package com.jeff.mud.command.get.finder;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.domain.item.domain.Container;

@Component
public class ContainerFinder {
private final List<Finder<Container>> finders;
	
	public ContainerFinder(List<Finder<Container>> finders) {
		this.finders = finders;
	}
	
	public Container findTarget(CommandDataCarrier input) {
		if (!input.hasTarget()) {
			return null;
		}
		
		for (Finder<Container> finder : finders) {
			Optional<Container> find = finder.find(input);
			if (find.isPresent()) {
				return find.get();
			}
		}
		
		return null;
	}
}
