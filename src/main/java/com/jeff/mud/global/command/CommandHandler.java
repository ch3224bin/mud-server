package com.jeff.mud.global.command;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
	private final List<Command> commands = new ArrayList<>();
	
	public void addCommand(Command command) {
		this.commands.add(command);
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
