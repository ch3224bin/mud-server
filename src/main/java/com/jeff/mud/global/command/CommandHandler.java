package com.jeff.mud.global.command;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
	private final List<Command> commands;
	
	public CommandHandler(List<Command> commands) {
		this.commands = commands;
	}
	
	public void handle(String msg) {
		Input input = new Input(msg);
		for (Command command : commands) {
			if (command.execute(input)) {
				break;
			}
		}
	}
}
