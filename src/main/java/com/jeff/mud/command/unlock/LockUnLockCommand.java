package com.jeff.mud.command.unlock;

import java.util.Optional;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Wayout;

public abstract class LockUnLockCommand extends Command {
	
	abstract protected LockUnlockTemplate getLockUnlockTemplate();
	
	@Override
	protected void handle(CommandDataCarrier input) {
		String target = input.getTarget();
		if (!Direction.contains(target)) {
			getLockUnlockTemplate().notContainsDirection(input);
			return;
		}
		
		Direction direction = Direction.valueOf(target);
		Optional<Wayout> wayout = input.getPlayer().getRoom().getWayoutByDirection(direction);
		
		if (wayout.isPresent() && wayout.get().isShow()) {
			if (getLockUnlockTemplate().checkLockOrUnlock(wayout.get().getDoor())) {
				// 소지품에서 열쇠 찾기
				if (input.getPlayer().getPlayerBag().hasKey(wayout.get().getDoor())) {
					getLockUnlockTemplate().matchedKey(input, wayout.get().getDoor(), direction);
				} else {
					getLockUnlockTemplate().notMatchedKey(input, direction);
				}
			} else {
				getLockUnlockTemplate().notLockedOrUnlocked(input, direction);
			}
		} else {
			getLockUnlockTemplate().notExitsWayout(input);
		}
	}
}
