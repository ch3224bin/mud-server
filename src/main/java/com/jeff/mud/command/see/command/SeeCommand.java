package com.jeff.mud.command.see.command;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.player.dao.PlayerRepository;
import com.jeff.mud.domain.room.dao.RoomRepository;
import com.jeff.mud.state.PlayerState;

@Component
public class SeeCommand implements Command {
	
	public SeeCommand(PlayerRepository playerRepository, RoomRepository roomRepository) {
		
	}

	@Override
	public CommandConstants commandConstants() {
		return CommandConstants.see;
	}

	@Override
	public void handle(CommandDataCarrier input) {
		// TODO 보는게 방인지 뭔지 찾아야함
		// 플레이어 불러오고
		// 방 불러오고
		// 대상에 따라 방 > 출구 > 방의 물품 > 내 소지품의 물품 등 순으로 찾아나가야함
		
	}

	@Override
	public List<PlayerState> allowStates() {
		return Arrays.asList(PlayerState.normal, PlayerState.combat);
	}

	@Override
	public void handleDenyState(PlayerState state) {
		// TODO Auto-generated method stub
		
	}
}
