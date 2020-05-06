package com.jeff.mud.global.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

@Component
public class CustomMessagingTemplate {
	private static final String USER_DESTINATION = "/history";
	private static final String TEMPLATES_PATH = "/templates/";
	
	private final Handlebars handlebars = new Handlebars();
	private final Map<String, Template> templateMap = new HashMap<>();
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	public CustomMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}
	
	public void convertAndSendToYou(String location, Object payload) {
		String message = getCompileString(location, payload);
		simpMessagingTemplate.convertAndSendToUser("user", USER_DESTINATION, message);
	}
	
	private String getCompileString(String location, Object payload) {
		try {
			Template template = templateMap.get(location);
			if (template == null) { // handlebars.compile(location) 연산을 피하기 위해
				template = handlebars.compile(TEMPLATES_PATH + location);
				templateMap.put(location, template);
			}
			
			return template.apply(payload);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
