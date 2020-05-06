package com.jeff.mud.global.command;

import lombok.Getter;

@Getter
public class Input {
	private String msg;
	private String command;
	
	public Input(String msg) {
		this.msg = msg;
		parse();
	}
	
	private void parse() {
		String[] words = msg.split(" +");
		int count = words.length;
		this.command = words[count-1];
	}
}
