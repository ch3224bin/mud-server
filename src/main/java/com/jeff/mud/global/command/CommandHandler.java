package com.jeff.mud.global.command;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeff.mud.domain.player.dao.PlayerRepository;
import com.jeff.mud.domain.player.domain.Player;

/**
 * 명령어 처리기
 * 
 * <p>명령어 리스트에서 맞는 명령어를 찾아 실행시킨다.</p>
 * 
 * <p>{@code CommandDataCarrier}에 한 주기 동안 필요한 것들을 담는다. ThreadLocal처럼</p>
 *
 * @author ChangHo Vin
 */
@Component
public class CommandHandler {
	private final List<Command> commands;
	private final PlayerRepository playerRepository;
	
	public CommandHandler(List<Command> commands, PlayerRepository playerRepository) {
		this.commands = commands;
		this.playerRepository = playerRepository;
	}
	
	public void handle(Principal principal, String msg) {
		Player player = playerRepository.findByUsername(principal.getName()).get();
		CommandDataCarrier input = new CommandDataCarrier(principal, player, msg);
		for (Command command : commands) {
			if (command.execute(input)) {
				break;
			}
		}
	}
}
