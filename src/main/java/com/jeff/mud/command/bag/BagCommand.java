package com.jeff.mud.command.bag;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.charactor.dto.CharactorBagDc;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;

@Component
public class BagCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	
	public BagCommand(CustomMessagingTemplate customMessagingTemplate) {
		this.customMessagingTemplate = customMessagingTemplate;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.normal, CharactorState.combat);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.bag;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		CharactorBagDc charactorBag = new CharactorBagDc(input.getPlayer().getCharactorBag());
		customMessagingTemplate.sendToYou(input.getUsername(), Template.playerBag, charactorBag);
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
