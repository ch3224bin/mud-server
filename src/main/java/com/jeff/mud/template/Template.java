package com.jeff.mud.template;

import com.jeff.mud.global.message.Pathable;

import lombok.Getter;

@Getter
public enum Template implements Pathable {
	who ("who"),
	stat ("stat"),
	shortStatus ("short_status"),
	welcome ("welcome"),
	defaultMessage ("default_message"),
	room ("room"),
	item ("item"),
	charactor ("charactor"),
	playerBag ("player_bag"),
	notFoundObject ("not_found_object"),
	seeDenyObject ("see_deny_object"),
	// 명령어 관련
	takeOutSendMe ("command/take_out_send_me"),
	takeOutSendRoom ("command/take_out_send_room"),
	getSendMe ("command/get_send_me"),
	getSendRoom ("command/get_send_room"),
	putSendMe ("command/put_send_me"),
	putSendRoom ("command/put_send_room"),
	moveOutSendRoom ("command/move_out_send_room"),
	moveInSendRoom ("command/move_in_send_room"),
	saySendMe ("command/say_send_me"),
	saySendRoom ("command/say_send_room"),
	;
	
	private String path;
	Template(String path) {
		this.path = path;
	}
}
