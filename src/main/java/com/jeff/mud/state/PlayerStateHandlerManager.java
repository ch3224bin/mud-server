package com.jeff.mud.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;

@Component
public class PlayerStateHandlerManager {
	
	private final Map<PlayerState, StateHandler> stateMap = new HashMap<>();
	
	public PlayerStateHandlerManager(List<StateHandler> stateHandlers) {
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
}
