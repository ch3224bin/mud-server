package com.jeff.mud.domain.charactor.dto;

import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.template.Template;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CharactorDc implements Seeable {
	private long id;
	private String username;
	private String name;
	private String flashState;
	private String temp1;
	private boolean isNpc;
	private String description;
	private boolean isAttackable;
	
	public CharactorDc(Charactor charactor) {
		this.id = charactor.getId();
		this.name = charactor.getName();
		this.isNpc = (charactor instanceof NonPlayer);
		if (this.isNpc) {
			this.description = ((NonPlayer) charactor).getDescription();
			this.isAttackable = ((NonPlayer) charactor).isAttackable();
		} else {
			this.username = ((Player) charactor).getAccount().getUsername();
		}
	}
	
	public void setFlashState(String flashState) {
		this.flashState = flashState;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	@Override
	public Template template() {
		return Template.charactor;
	}
}
