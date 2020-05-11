package com.jeff.mud.command;

import java.util.List;

import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.state.PlayerState;

/**
 * 명령어 처리 인터페이스
 * 
 * <p>
 * CommandConstants의 명령어와 비교하여 일치여부를 판단하고<br>
 * handle() 메소드에 처리할 일을 위임한다.
 * </p>
 * 
 * @author ChangHo Vin
 *
 */
public abstract class Command {
	public boolean execute(CommandDataCarrier input) {
		if (!commandConstants().matched(input.getCommand())) {
			return false;
		}
		// 명령어에는 해당하지만 상태가 맞지 않을때
		if (allowStates().stream()
				.noneMatch(x -> x == input.getPlayer().getState())) {
			handleDenyState(input);
			return true;
		}
		
		handle(input);
		return true;
	}
	protected abstract List<PlayerState> allowStates();
	protected abstract CommandConstants commandConstants();
	protected abstract void handle(CommandDataCarrier input);
	protected abstract void handleDenyState(CommandDataCarrier input);
}
