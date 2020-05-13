package com.jeff.mud.command;

import java.util.Arrays;

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
	private String adverb;
	private String secondTarget;
	private String words;
	
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
			this.words = String.join(" ", Arrays.copyOfRange(words, 0, length-1));
		}
		if (length == 3) {
			this.secondTarget = this.adverb = words[1];
		}
		if (length > 3) {
			this.secondTarget = words[1];
			this.adverb = String.join(" ", Arrays.copyOfRange(words, 2, length-1));
		}
	}
	
	public boolean hasTarget() {
		return !Strings.isNullOrEmpty(target);
	}
	
	public boolean hasSecondTarget() {
		return !Strings.isNullOrEmpty(secondTarget);
	}
	
	public boolean hasAdverb() {
		return !Strings.isNullOrEmpty(adverb);
	}
}
