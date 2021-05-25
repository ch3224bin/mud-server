package com.jeff.mud.global.message;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.template.Template;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomMessagingTemplate {
	private static final String USER_DESTINATION = "/history";
	private static final String STATUS_DESTINATION = "/history/status";

	private final SimpMessagingTemplate simpMessagingTemplate;
	private final PlayerRepository playerRepository;

	public CustomMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate, PlayerRepository playerRepository) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.playerRepository = playerRepository;
	}

	public void sendToYou(String username, Template template, Object payload) {
		simpMessagingTemplate.convertAndSendToUser(username, USER_DESTINATION, new Message(template, payload));
	}

	public void sendToRoomWithOutMe(CommandDataCarrier input, Template template, Object payload) {
		sendToRoomWithOutMe(input.getPlayer(), template, payload);
	}

	public void sendToRoomWithOutMe(Player player, Template template, Object payload) {
		List<Player> another = playerRepository.findByRoomNotExistsMe(player.getRoom(), player);
		for (Player p : another) {
			sendToYou(p.getAccount().getUsername(), template, payload);
		}
	}

	public void sendToRoom(Player player, Template template, Object payload) {
		List<Player> everyone = playerRepository.findByRoom(player.getRoom());
		for (Player p : everyone) {
			sendToYou(p.getAccount().getUsername(), template, payload);
		}
	}

	public void sendShortStatusToYou(String username, Object payload) {
		simpMessagingTemplate.convertAndSendToUser(username, STATUS_DESTINATION, payload);
	}
}
