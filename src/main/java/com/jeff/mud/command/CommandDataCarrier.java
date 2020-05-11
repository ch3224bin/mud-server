package com.jeff.mud.command;

import com.google.common.base.Strings;
import com.jeff.mud.domain.player.domain.Player;

import lombok.Getter;

@Getter
public class CommandDataCarrier {
	private String username;
	private Player player;
	private String msg;
	private String target;
	private String command;
	
	public CommandDataCarrier(String username, Player player, String msg) {
		this.username = username;
		this.player = player;
		this.msg = msg;
		parse();
	}
	
	private void parse() {
		String[] words = msg.split(" +");
		int length = words.length;
		this.command = words[length-1];
		if (length > 1) {
			this.target = words[0];
		}
	}
	
	public boolean hasTarget() {
		return !Strings.isNullOrEmpty(target);
	}
}
