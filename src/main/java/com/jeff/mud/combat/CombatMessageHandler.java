package com.jeff.mud.combat;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import com.jeff.mud.combat.constants.Judgments;
import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.global.message.CustomMessagingTemplate;
import com.jeff.mud.template.CombatTempleteDc;
import com.jeff.mud.template.Template;

@Component
public class CombatMessageHandler {
	private static Map<Judgments, Handler> handlers = new HashMap<>();
	private final CustomMessagingTemplate customMessagingTemplate;
	
	public CombatMessageHandler (CustomMessagingTemplate customMessagingTemplate) {
		this.customMessagingTemplate = customMessagingTemplate;
		
		handlers.put(Judgments.SUCCESS, new SuccessHandler());
		handlers.put(Judgments.FAIL, new FailHandler());
		handlers.put(Judgments.EXTREME, new SuccessHandler());
		handlers.put(Judgments.FUMBLE, new FailHandler());
	}
	
	public void sendCombatMessage(Judgments judgments, Charactor attacker, Charactor defender, String message, int damege) {
		Handler handler = handlers.get(judgments);
		String methodName = attacker.getClass().getName();
		methodName = "do" + methodName.substring(methodName.lastIndexOf('.') + 1);
		Method method = ReflectionUtils.findMethod(handler.getClass(), methodName, Charactor.class, Charactor.class, String.class, int.class);
		ReflectionUtils.makeAccessible(method);
		ReflectionUtils.invokeMethod(method, handler, attacker, defender, message, damege);
	}
	
	
	abstract class Handler {
		
		void doPlayer(Charactor attacker, Charactor defender, String message, int damege) {
			CombatTempleteDc combatTempleteDc = new CombatTempleteDc(attacker.getName(), defender.getName(), message, damege);
			customMessagingTemplate.sendToYou(((Player)attacker).getAccount().getUsername(), playerSendMe(), combatTempleteDc);
			customMessagingTemplate.sendToRoomWithOutMe((Player)attacker, playerSendRoom(), combatTempleteDc);
		}
		
		void doNonPlayer(Charactor attacker, Charactor defender, String message, int damege) {
			CombatTempleteDc combatTempleteDc = new CombatTempleteDc(attacker.getName(), defender.getName(), message, damege);
			customMessagingTemplate.sendToYou(((Player) defender).getAccount().getUsername(), npcSendMe(), combatTempleteDc);
			customMessagingTemplate.sendToRoomWithOutMe((Player) defender, npcSendRoom(), combatTempleteDc);
		}
		
		abstract Template playerSendMe();
		abstract Template playerSendRoom();
		abstract Template npcSendMe();
		abstract Template npcSendRoom();
	}
	
	class SuccessHandler extends Handler {
		@Override
		Template playerSendMe() {
			return Template.combatPlayerAttackSuccessSendMe;
		}

		@Override
		Template playerSendRoom() {
			return Template.combatPlayerAttackSuccessSendRoom;
		}

		@Override
		Template npcSendMe() {
			return Template.combatNpcAttackSuccessSendMe;
		}

		@Override
		Template npcSendRoom() {
			return Template.combatNpcAttackSuccessSendRoom;
		}
	}
	
	class FailHandler extends Handler {
		@Override
		Template playerSendMe() {
			return Template.combatPlayerAttackFailSendMe;
		}

		@Override
		Template playerSendRoom() {
			return Template.combatPlayerAttackFailSendRoom;
		}

		@Override
		Template npcSendMe() {
			return Template.combatNpcAttackFailSendMe;
		}

		@Override
		Template npcSendRoom() {
			return Template.combatNpcAttackFailSendRoom;
		}
	}
}
