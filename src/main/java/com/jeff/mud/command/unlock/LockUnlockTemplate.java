package com.jeff.mud.command.unlock;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.room.domain.Door;

public interface LockUnlockTemplate {
	void notContainsDirection(CommandDataCarrier input);
	void matchedKey(CommandDataCarrier input, Door door, String direction);
	void notMatchedKey(CommandDataCarrier input, String direction);
	void notLockedOrUnlocked(CommandDataCarrier input, String direction);
	void notExitsWayout(CommandDataCarrier input);
	boolean checkLockOrUnlock(Door door);
}
