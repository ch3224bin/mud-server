package com.jeff.mud.command.move;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.charactor.service.CharactorService;
import com.jeff.mud.domain.room.RoomService;
import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.domain.room.domain.Wayout;
import com.jeff.mud.domain.room.dto.RoomDc;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;
import com.jeff.mud.template.TemplateDc;

@Component
public class MoveCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final RoomService roomService;
	private final CharactorService charactorService;
	
	public MoveCommand(CustomMessagingTemplate customMessagingTemplate, RoomService roomService, CharactorService charactorService) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.roomService = roomService;
		this.charactorService = charactorService;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.NORMAL);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.move;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		Direction direction = Direction.valueOfString(input.getCommand());
		Optional<Wayout> wayout = input.getPlayer().getRoom().getWayoutByDirection(direction);
		if (wayout.isPresent() && wayout.get().isShow()) {
			if (!wayout.get().getDoor().isLocked()) {
				Room nextRoom = wayout.get().getNextRoom();
				
				TemplateDc td = TemplateDc.builder().player(input.getPlayer().getName()).direction(wayout.get().getDirection().getName()).build();
				customMessagingTemplate.sendToRoomWithOutMe(input, Template.moveOutSendRoom, td);
				
				input.getPlayer().moveTo(nextRoom);
				RoomDc roomDc = roomService.getRoomDcWithItems(input.getPlayer().getRoom(), charactorService.getCharactorsInTheRoomWithOutMe(input));
				customMessagingTemplate.sendToYou(input.getUsername(), Template.room, roomDc);
				customMessagingTemplate.sendToRoomWithOutMe(input, Template.moveInSendRoom, td);
			} else {
				customMessagingTemplate.sendToYou(input.getUsername(),
						Template.defaultMessage,
						String.format("%s 쪽으로 가는 문이 잠겨있습니다.", input.getCommand()));
			}
			return;
		}
		
		customMessagingTemplate.sendToYou(input.getUsername(),
				Template.defaultMessage,
				String.format("%s 쪽에는 길이 없습니다.", input.getCommand()));
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		if (CharactorState.COMBAT == input.getPlayer().getState()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, "전투중에는 이동할 수 없습니다.");
		}
	}
}
