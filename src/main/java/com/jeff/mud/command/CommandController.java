package com.jeff.mud.command;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CommandController {
	
	private final CommandHandler commandHandler;
	
	public CommandController(CommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}
	
	@MessageMapping("/command")
    public void send(Principal principal, String msg) throws Exception {
		commandHandler.handle(principal, msg);
    }
}
