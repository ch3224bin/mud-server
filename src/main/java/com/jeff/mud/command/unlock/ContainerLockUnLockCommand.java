package com.jeff.mud.command.unlock;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.get.finder.ContainerFinder;
import com.jeff.mud.domain.item.domain.Container;

@Component
public class ContainerLockUnLockCommand implements LockUnLockCommand {
	
	private final ContainerFinder containerFinder;
	
	public ContainerLockUnLockCommand(ContainerFinder containerFinder) {
		this.containerFinder = containerFinder;
	}
	
	@Override
	public void handle(CommandDataCarrier input, LockUnlockTemplate lockUnlockTemplate) {
		Container container = containerFinder.findTarget(input);
		if (container == null || container.getDoor() == null) {
			lockUnlockTemplate.notExitsWayout(input);
			return;
		}
		
		lockOrUnlock(input, lockUnlockTemplate, container.getDoor(), container.getName());
	}
}
