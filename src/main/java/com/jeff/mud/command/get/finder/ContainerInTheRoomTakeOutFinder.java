package com.jeff.mud.command.get.finder;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.domain.item.domain.Container;
import com.jeff.mud.domain.item.service.ContainerItemBrokerService;

@Component
public class ContainerInTheRoomTakeOutFinder implements Finder<Container> {
	
	private final ContainerItemBrokerService containerItemBrokerService;
	
	public ContainerInTheRoomTakeOutFinder(ContainerItemBrokerService containerItemBrokerService) {
		this.containerItemBrokerService = containerItemBrokerService;
	}

	@Override
	public Optional<Container> find(CommandDataCarrier input) {
		return containerItemBrokerService.getContainerByRoom(input.getPlayer().getRoom());
	}

}
