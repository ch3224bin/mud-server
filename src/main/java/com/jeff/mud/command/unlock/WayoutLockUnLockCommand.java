package com.jeff.mud.command.unlock;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Wayout;

@Component
public class WayoutLockUnLockCommand implements LockUnLockCommand {
	
	@Override
	public void handle(CommandDataCarrier input, LockUnlockTemplate lockUnlockTemplate) {
		String target = input.getTarget();
		if (!Direction.contains(target)) {
			lockUnlockTemplate.notContainsDirection(input);
			return;
		}
		
		Direction direction = Direction.valueOfString(target);
		Optional<Wayout> wayout = input.getPlayer().getRoom().getWayoutByDirection(direction);
		
		if (wayout.isPresent() && wayout.get().isShow()) {
			lockOrUnlock(input, lockUnlockTemplate, wayout.get().getDoor(), direction.getName());
		} else {
			lockUnlockTemplate.notExitsWayout(input);
		}
	}
}
