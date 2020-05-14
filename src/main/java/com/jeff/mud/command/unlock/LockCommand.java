package com.jeff.mud.command.unlock;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Door;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;

@Component
public class LockCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final WayoutLockUnLockCommand wayoutLockUnLockCommand;
	private final ContainerLockUnLockCommand containerLockUnLockCommand;
	
	public LockCommand(CustomMessagingTemplate customMessagingTemplate, WayoutLockUnLockCommand wayoutLockUnLockCommand, ContainerLockUnLockCommand containerLockUnLockCommand) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.wayoutLockUnLockCommand = wayoutLockUnLockCommand;
		this.containerLockUnLockCommand = containerLockUnLockCommand;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.normal);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.lock;
	}
	
	@Override
	protected void handle(CommandDataCarrier input) {
		String target = input.getTarget();
		if (Direction.contains(target)) {
			wayoutLockUnLockCommand.handle(input, getLockUnlockTemplate());
		} else {
			containerLockUnLockCommand.handle(input, getLockUnlockTemplate());
		}
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}
	
	protected LockUnlockTemplate getLockUnlockTemplate() {
		return new LockUnlockTemplate() {

			@Override
			public void notContainsDirection(CommandDataCarrier input) {
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s은(는) 잠글 수 없습니다.", input.getTarget()));
			}

			@Override
			public void matchedKey(CommandDataCarrier input, Door door, String direction) {
				door.lock();
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("당신은 %s문을 잠궜습니다.", direction));
				customMessagingTemplate.convertAndSendToRoomWithOutMe(input, Template.defaultMessage, String.format("%s이(가) %s문을 잠궜습니다.", input.getPlayer().getName(), direction));
			}

			@Override
			public void notMatchedKey(CommandDataCarrier input, String direction) {
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("당신에게는 %s문에 맞는 열쇠가 없습니다.", direction));
			}

			@Override
			public void notLockedOrUnlocked(CommandDataCarrier input, String direction) {
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s문은 이미 잠겨있습니다.", direction));
			}

			@Override
			public void notExitsWayout(CommandDataCarrier input) {
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s은(는) 잠글 수 없습니다.", input.getTarget()));
			}

			@Override
			public boolean checkLockOrUnlock(Door door) {
				return !door.isLocked();
			}
		};
	}
}
