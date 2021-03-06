package com.jeff.mud.command.say;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;
import com.jeff.mud.template.TemplateDc;

@Component
public class SayCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	
	public SayCommand(CustomMessagingTemplate customMessagingTemplate) {
		this.customMessagingTemplate = customMessagingTemplate;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.NORMAL, CharactorState.COMBAT);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.say;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		TemplateDc td = TemplateDc.builder().player(input.getPlayer().getName()).words(input.getWords()).build();
		customMessagingTemplate.sendToYou(input.getUsername(), Template.saySendMe, td);
		customMessagingTemplate.sendToRoomWithOutMe(input, Template.saySendRoom, td);
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
