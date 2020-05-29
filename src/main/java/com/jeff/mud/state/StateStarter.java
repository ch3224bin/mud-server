package com.jeff.mud.state;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.state.event.GameStartEvent;

@Component
public class StateStarter {
	
	private final PlayerStateHandler playerStateHandler;
	
	public StateStarter(PlayerStateHandler playerStateHandler) {
		this.playerStateHandler = playerStateHandler;
	}
	
	@EventListener
	public void start(GameStartEvent gameStartEvent) {
		Player player = (Player) gameStartEvent.getSource();
		CommandDataCarrier dc = new CommandDataCarrier(player.getAccount().getUsername(), player, "봐");
		playerStateHandler.handle(player.getState(), player.getState().getCommandConstants(), dc);
	}
}
