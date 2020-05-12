package com.jeff.mud.command.bag;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.player.dto.PlayerBagDc;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.PlayerState;
import com.jeff.mud.template.Template;

@Component
public class BagCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	
	public BagCommand(CustomMessagingTemplate customMessagingTemplate) {
		this.customMessagingTemplate = customMessagingTemplate;
	}

	@Override
	protected List<PlayerState> allowStates() {
		return Arrays.asList(PlayerState.normal, PlayerState.combat);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.bag;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		PlayerBagDc playerBag = new PlayerBagDc(input.getPlayer().getPlayerBag());
		customMessagingTemplate.convertAndSendToYou(input.getUsername(), Template.playerBag, playerBag);
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
