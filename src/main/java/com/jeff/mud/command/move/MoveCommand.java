package com.jeff.mud.command.move;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.room.RoomService;
import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.domain.room.domain.Wayout;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.template.Template;
import com.jeff.mud.template.TemplateDc;

@Component
public class MoveCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final RoomService roomService;
	
	public MoveCommand(CustomMessagingTemplate customMessagingTemplate, RoomService roomService) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.roomService = roomService;
	}

	@Override
	protected List<PlayerState> allowStates() {
		return Arrays.asList(PlayerState.normal);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.move;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		Direction direction = Direction.valueOf(input.getCommand());
		Optional<Wayout> wayout = input.getPlayer().getRoom().getWayoutByDirection(direction);
		if (wayout.isPresent() && wayout.get().isShow()) {
			if (!wayout.get().getDoor().isLocked()) {
				Room nextRoom = wayout.get().getNextRoom();
				
				TemplateDc td = TemplateDc.builder().player(input.getPlayer().getName()).direction(wayout.get().getDirection().toString()).build();
				customMessagingTemplate.convertAndSendToRoomWithOutMe(input, Template.moveOutSendRoom, td);
				
				input.getPlayer().moveTo(nextRoom);
				customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.room, roomService.getRoomDcWithItemsAndOnlinePlayers(nextRoom, input.getPlayer()));
				customMessagingTemplate.convertAndSendToRoomWithOutMe(input, Template.moveInSendRoom, td);
			} else {
				customMessagingTemplate.convertAndSendToYou(input.getUsername(),
						Template.defaultMessage,
						String.format("%s 쪽으로 가는 문이 잠겨있습니다.", input.getCommand()));
			}
			return;
		}
		
		customMessagingTemplate.convertAndSendToYou(input.getUsername(),
				Template.defaultMessage,
				String.format("%s 쪽에는 길이 없습니다.", input.getCommand()));
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		if (PlayerState.combat == input.getPlayer().getState()) {
			customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.defaultMessage, "전투중에는 이동할 수 없습니다.");
		}
	}
}
