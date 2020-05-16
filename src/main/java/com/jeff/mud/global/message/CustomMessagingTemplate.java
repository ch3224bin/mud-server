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
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Player;

@Component
public class CustomMessagingTemplate {
	private static final String USER_DESTINATION = "/history";
	private static final String STATUS_DESTINATION = "/history/status";
	private static final String TEMPLATES_PATH = "/templates/";
	
	private final Handlebars handlebars = new Handlebars();
	private final Map<String, Template> templateMap = new HashMap<>();
	private final SimpMessagingTemplate simpMessagingTemplate;
	private final PlayerRepository playerRepository;
	
	public CustomMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate, PlayerRepository playerRepository) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.playerRepository = playerRepository;
	}
	
	public void sendToYou(String username, Pathable pathable, Object payload) {
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

	public void sendToRoomWithOutMe(CommandDataCarrier input, Pathable pathable, Object payload) {
		sendToRoomWithOutMe(input.getPlayer(), pathable, payload);
	}
	
	public void sendToRoomWithOutMe(Player player, Pathable pathable, Object payload) {
		List<Player> another = playerRepository.findByRoomNotExistsMe(player.getRoom(), player);
		for (Player p : another) {
			sendToYou(p.getAccount().getUsername(), pathable, payload);
		}
	}
	
	public void sendToRoom(Player player, Pathable pathable, Object payload) {
		List<Player> everyone = playerRepository.findByRoom(player.getRoom());
		for (Player p : everyone) {
			sendToYou(p.getAccount().getUsername(), pathable, payload);
		}
	}
	
	public void sendShortStatusToYou(String username, Pathable pathable, Object payload) {
		String message = getCompileString(pathable, payload);
		simpMessagingTemplate.convertAndSendToUser(username, STATUS_DESTINATION, message);
	}
}
