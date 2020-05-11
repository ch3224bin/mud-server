package com.jeff.mud.command.see.command;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.command.see.finder.SeeFinder;
import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;

@Component
public class SeeCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final SeeFinder seeFinder = new SeeFinder();
	
	public SeeCommand(CustomMessagingTemplate customMessagingTemplate) {
		this.customMessagingTemplate = customMessagingTemplate;
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.see;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		Seeable target = seeFinder.findTarget(input);
		customMessagingTemplate.convertAndSendToYou(input.getUsername(), target.template(), target);
	}

	@Override
	protected List<PlayerState> allowStates() {
		return Arrays.asList(PlayerState.normal, PlayerState.combat);
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		// TODO Auto-generated method stub
		
	}
}
