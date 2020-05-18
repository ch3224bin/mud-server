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
		
		handlers.put(Judgments.success, new SuccessHandler());
		handlers.put(Judgments.fail, new FailHandler());
		handlers.put(Judgments.critical, new SuccessHandler());
		handlers.put(Judgments.fumble, new FailHandler());
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
			customMessagingTemplate.sendToYou(((Player)attacker).getAccount().getUsername(), playerAttacSuccessSendMe(), combatTempleteDc);
			customMessagingTemplate.sendToRoomWithOutMe((Player)attacker, playerAttacSuccessSendRoom(), combatTempleteDc);
		}
		
		void doNonPlayer(Charactor attacker, Charactor defender, String message, int damege) {
			CombatTempleteDc combatTempleteDc = new CombatTempleteDc(attacker.getName(), defender.getName(), message, damege);
			customMessagingTemplate.sendToRoom((Player) defender, npcAttacSuccessSendMe(), combatTempleteDc);
			customMessagingTemplate.sendToRoom((Player) defender, npcAttacSuccessSendRoom(), combatTempleteDc);
		}
		
		abstract Template playerAttacSuccessSendMe();
		abstract Template playerAttacSuccessSendRoom();
		abstract Template npcAttacSuccessSendMe();
		abstract Template npcAttacSuccessSendRoom();
	}
	
	class SuccessHandler extends Handler {
		@Override
		Template playerAttacSuccessSendMe() {
			return Template.combatPlayerAttackSuccessSendMe;
		}

		@Override
		Template playerAttacSuccessSendRoom() {
			return Template.combatPlayerAttackSuccessSendRoom;
		}

		@Override
		Template npcAttacSuccessSendMe() {
			return Template.combatNpcAttackSuccessSendMe;
		}

		@Override
		Template npcAttacSuccessSendRoom() {
			return Template.combatPlayerAttackSuccessSendRoom;
		}
	}
	
	class FailHandler extends Handler {
		@Override
		Template playerAttacSuccessSendMe() {
			return Template.combatPlayerAttackFailSendMe;
		}

		@Override
		Template playerAttacSuccessSendRoom() {
			return Template.combatPlayerAttackFailSendRoom;
		}

		@Override
		Template npcAttacSuccessSendMe() {
			return Template.combatNpcAttackFailSendMe;
		}

		@Override
		Template npcAttacSuccessSendRoom() {
			return Template.combatPlayerAttackFailSendRoom;
		}
	}
}
