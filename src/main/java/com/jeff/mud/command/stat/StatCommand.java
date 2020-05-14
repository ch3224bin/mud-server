package com.jeff.mud.command.stat;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.stat.dto.StatTemplateDc;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;

@Component
public class StatCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	
	public StatCommand(CustomMessagingTemplate customMessagingTemplate) {
		this.customMessagingTemplate = customMessagingTemplate;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.normal, CharactorState.combat);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.stat;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		customMessagingTemplate.sendToYou(input.getUsername(), Template.stat, new StatTemplateDc(input.getPlayer()));
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
