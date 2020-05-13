package com.jeff.mud.global.message;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.player.dao.PlayerRepository;
import com.jeff.mud.domain.player.domain.Player;

@Component
public class CustomMessagingTemplate {
	private static final String USER_DESTINATION = "/history";
	private static final String TEMPLATES_PATH = "/templates/";
	
	private final Handlebars handlebars = new Handlebars();
	private final Map<String, Template> templateMap = new HashMap<>();
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final PlayerRepository playerRepository;
	
	public CustomMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate, PlayerRepository playerRepository) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.playerRepository = playerRepository;
	}
	
	public void convertAndSendToYou(String username, Pathable pathable, Object payload) {
		String message = getCompileString(pathable, payload);
		simpMessagingTemplate.convertAndSendToUser(username, USER_DESTINATION, message);
	}
	
	private String getCompileString(Pathable pathable, Object payload) {
		try {
			String location = pathable.getPath();
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

	public void convertAndSendToRoomWithOutMe(CommandDataCarrier input, Pathable pathable, Object payload) {
		List<Player> another = playerRepository.findByRoomNotExistsMe(input.getPlayer().getRoom(), input.getPlayer());
		for (Player p : another) {
			convertAndSendToYou(p.getAccount().getUsername(), pathable, payload);
		}
	}
}
