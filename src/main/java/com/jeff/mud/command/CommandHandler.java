package com.jeff.mud.command;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.player.dao.PlayerRepository;
import com.jeff.mud.domain.player.domain.Player;
import com.jeff.mud.state.PlayerStateHandlerManager;

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
	private final Map<CommandConstants, Command> commandMap = new HashMap<>();
	private final PlayerRepository playerRepository;
	private final PlayerStateHandlerManager playerStateHandlerManager;
	
	public CommandHandler(List<Command> commands,
			PlayerRepository playerRepository,
			PlayerStateHandlerManager playerStateHandlerManager) {
		this.playerRepository = playerRepository;
		this.playerStateHandlerManager = playerStateHandlerManager;
		for (Command commmand : commands) {
			commandMap.put(commmand.commandConstants(), commmand);
		}
	}
	
	public Command getCommand(CommandConstants commandConstants) {
		return commandMap.get(commandConstants);
	}
	
	public void handle(Principal principal, String msg) {
		Player player = playerRepository.findByUsername(principal.getName()).get();
		CommandDataCarrier dc = new CommandDataCarrier(principal, player, msg);
		for (Command command : commandMap.values()) {
			boolean executed = playerStateHandlerManager.handle(player.getState(), command, dc);
			if (executed) {
				break;
			}
		}
	}
}
