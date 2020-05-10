package com.jeff.mud.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class StateHandlerManager {
	
	private final static Map<PlayerState, StateHandler> stateMap = new HashMap<>();
	
	
	public StateHandlerManager(List<StateHandler> stateHandlers) {
		for (StateHandler handler : stateHandlers) {
			stateMap.put(handler.state(), handler);
		}
	}

	public static StateHandler getStateHandler(PlayerState state) {
		return stateMap.get(state);
	}
	
}
