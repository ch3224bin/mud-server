package com.jeff.mud.global.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class CommandController {
	
	private final SimpMessagingTemplate template;
	
	public CommandController(SimpMessagingTemplate template) {
		this.template = template;
	}
	
	@MessageMapping("/command")
    public void send(String msg) throws Exception {
		// template path와 body를 받아 전달은.. 누가 할까..
		// 입력을 받아 위임...
		template.convertAndSendToUser("user", "/history", "hello~ " + msg);
    }
}
