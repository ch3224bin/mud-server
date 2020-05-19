package com.jeff.mud.command.attack;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.combat.CombatManager;
import com.jeff.mud.command.Command;
import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.constants.CommandConstants;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.charactor.service.CharactorService;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.state.CharactorState;
import com.jeff.mud.template.Template;

@Component
public class AttackCommand extends Command {
	
	private final CustomMessagingTemplate customMessagingTemplate;
	private final CharactorService charactorService;
	private final CombatManager combatZone;
	
	public AttackCommand(CustomMessagingTemplate customMessagingTemplate, CharactorService charactorService, CombatManager combatZone) {
		this.customMessagingTemplate = customMessagingTemplate;
		this.charactorService = charactorService;
		this.combatZone = combatZone;
	}

	@Override
	protected List<CharactorState> allowStates() {
		return Arrays.asList(CharactorState.NORMAL);
	}

	@Override
	protected CommandConstants commandConstants() {
		return CommandConstants.attack;
	}

	@Override
	protected void handle(CommandDataCarrier input) {
		List<CharactorDc> charactors = charactorService.getCharactorsInTheRoomWithOutMe(input);
		Optional<CharactorDc> charactor = charactors.stream()
			.filter(CharactorDc::isNpc)
			.filter(c -> {
				String[] names = c.getName().split(" ");
				for (String name : names) {
					if (name.startsWith(input.getTarget())) {
						return true;
					}
				}
				return false;
			}).findFirst();
		
		if (charactor.isEmpty()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("%s이(가) 없습니다.", input.getTarget()));
			return;
		}
		
		if (!charactor.get().isAttackable()) {
			customMessagingTemplate.sendToYou(input.getUsername(), Template.defaultMessage, String.format("%s은(는) 공격할 수 없습니다.", charactor.get().getName()));
			return;
		}
		
		// 전투로 전환
		combatZone.startCombat(new CharactorDc(input.getPlayer()), charactor.get());
	}

	@Override
	protected void handleDenyState(CommandDataCarrier input) {
		
	}

}
