package com.jeff.mud.state;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.CommandHandler;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.player.domain.Player;

@Component
public class PlayerStateHandlerManager {
	
	private final Map<PlayerState, StateHandler> stateMap = new HashMap<>();
	private final CommandHandler commandHandler;
	
	public PlayerStateHandlerManager(List<StateHandler> stateHandlers, CommandHandler commandHandler) {
		this.commandHandler = commandHandler;
		for (StateHandler handler : stateHandlers) {
			stateMap.put(handler.state(), handler);
		}
	}

	public boolean handle(PlayerState state, Command command, CommandDataCarrier dc) {
		if (stateMap.containsKey(state)) {
			stateMap.get(state).handle(command, dc);
		}
		
		return command.execute(dc);
	}
	
	public void welcome(Principal principal, Player player, PlayerState state) {
		CommandDataCarrier dc = new CommandDataCarrier(principal, player, "봐");
		if (state == PlayerState.normal) {
			handle(state, commandHandler.getCommand(CommandConstants.see), dc);
			return;
		}
		
		handle(state, commandHandler.getCommand(CommandConstants.noop), dc);
	}
}
