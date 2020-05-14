package com.jeff.mud.command;

import java.security.Principal;
import java.util.Collection;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.mud.domain.charactor.dao.PlayerRepository;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.state.PlayerStateHandler;

/**
 * 명령어 처리기
 * 
 * <p>명령어 리스트에서 맞는 명령어를 찾아 실행시킨다.</p>
 * 
 * <p>{@code CommandDataCarrier}에 한 주기 동안 필요한 것들을 담는다. ThreadLocal처럼</p>
 *
 * @author ChangHo Vin
 */
@Transactional
@Component
public class CommandHandler {
	private final CommandManager commandManager;
	private final PlayerRepository playerRepository;
	private final PlayerStateHandler playerStateHandler;
	
	public CommandHandler(CommandManager commandManager,
			PlayerRepository playerRepository,
			PlayerStateHandler playerStateHandler) {
		this.commandManager = commandManager;
		this.playerRepository = playerRepository;
		this.playerStateHandler = playerStateHandler;
	}
	
	public void handle(Principal principal, String msg) {
		Player player = playerRepository.findByUsername(principal.getName()).get();
		CommandDataCarrier dc = new CommandDataCarrier(principal.getName(), player, msg.strip());
		Collection<Command> commands = commandManager.getCommands();
		for (Command command : commands) {
			boolean executed = playerStateHandler.handle(player.getState(), command, dc);
			if (executed) {
				break;
			}
		}
	}
}
