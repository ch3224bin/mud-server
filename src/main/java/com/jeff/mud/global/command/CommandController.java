package com.jeff.mud.global.command;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CommandController {
	
	private final CommandHandler commandHandler;
	
	public CommandController(CommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}
	
	@MessageMapping("/command")
    public void send(String msg) throws Exception {
		commandHandler.handle(msg);
    }
}
