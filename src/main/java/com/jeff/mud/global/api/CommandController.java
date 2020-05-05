package com.jeff.mud.global.api;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CommandController {
	@MessageMapping("/command")
    @SendTo("/history")
    public String send(String msg) throws Exception {
        return "hello~ " + msg;
    }
}
