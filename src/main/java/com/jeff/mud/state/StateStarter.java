package com.jeff.mud.state;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.charactor.domain.Player;

@Component
public class StateStarter {
	
	private final PlayerStateHandler playerStateHandler;
	
	public StateStarter(PlayerStateHandler playerStateHandler) {
		this.playerStateHandler = playerStateHandler;
	}
	
	public void start(String username, Player player) {
		CommandDataCarrier dc = new CommandDataCarrier(username, player, "봐");
		playerStateHandler.handle(player.getState(), player.getState().getCommandConstants(), dc);
	}
}
