package com.jeff.mud.command.unlock;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.room.domain.Door;

public interface LockUnLockCommand {
	void handle(CommandDataCarrier input, LockUnlockTemplate lockUnlockTemplate);
	
	default void lockOrUnlock(CommandDataCarrier input, LockUnlockTemplate lockUnlockTemplate, Door door, String name) {
		if (lockUnlockTemplate.checkLockOrUnlock(door)) {
			// 소지품에서 열쇠 찾기
			if (input.getPlayer().getPlayerBag().hasKey(door)) {
				lockUnlockTemplate.matchedKey(input, door, name);
			} else {
				lockUnlockTemplate.notMatchedKey(input, name);
			}
		} else {
			lockUnlockTemplate.notLockedOrUnlocked(input, name);
		}
	}
}
