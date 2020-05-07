package com.jeff.mud.global.command;

import java.security.Principal;

import com.jeff.mud.domain.player.domain.Player;

import lombok.Getter;

@Getter
public class CommandDataCarrier {
	private String username;
	private Player player;
	private String msg;
	private String command;
	
	public CommandDataCarrier(Principal principal, Player player, String msg) {
		this.username = principal.getName();
		this.player = player;
		this.msg = msg;
		parse();
	}
	
	private void parse() {
		String[] words = msg.split(" +");
		int count = words.length;
		this.command = words[count-1];
	}
}
