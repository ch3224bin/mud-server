package com.jeff.mud.command.unlock;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Door;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.template.Template;

@Component
public class UnlockCommand extends LockUnLockCommand {
	private final CustomMessagingTemplate customMessagingTemplate;
	
	public UnlockCommand(CustomMessagingTemplate customMessagingTemplate) {
		this.customMessagingTemplate = customMessagingTemplate;
	}

	@Override
	protected List<PlayerState> allowStates() {
		return Arrays.asList(PlayerState.normal);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.unlock;
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

	@Override
	protected LockUnlockTemplate getLockUnlockTemplate() {
		return new LockUnlockTemplate() {

			@Override
			public void notContainsDirection(CommandDataCarrier input) {
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s은(는) 열 수 없습니다.", input.getTarget()));
			}

			@Override
			public void matchedKey(CommandDataCarrier input, Door door, Direction direction) {
				door.unlock();
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("당신은 %s문을 열었습니다.", direction));
				customMessagingTemplate.convertAndSendToRoomWithOutMe(input, Template.defaultMessage, String.format("%s이(가) %s문을 열었습니다.", input.getPlayer().getName(), direction));
			}

			@Override
			public void notMatchedKey(CommandDataCarrier input, Direction direction) {
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("당신에게는 %s문에 맞는 열쇠가 없습니다.", direction));
			}

			@Override
			public void notLockedOrUnlocked(CommandDataCarrier input, Direction direction) {
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s문은 잠기지 않았습니다.", direction));
			}

			@Override
			public void notExitsWayout(CommandDataCarrier input) {
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, String.format("%s은(는) 열 수 없습니다.", input.getTarget()));
			}
			
			@Override
			public boolean checkLockOrUnlock(Door door) {
				return door.isLocked();
			}
		};
	}
}
