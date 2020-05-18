package com.jeff.mud.domain.item.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.jeff.mud.domain.item.constants.ItemGrade;
import com.jeff.mud.domain.item.constants.Weapons;
import com.jeff.mud.domain.stat.rule.DiceRule;
import com.jeff.mud.global.domain.model.Typeable;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "weapon")
@DiscriminatorValue("weapon")
@Getter
public class Weapon extends Item implements Typeable<Weapons> {
	@Enumerated(EnumType.STRING)
	@Column(name = "weapon_type", nullable = false, updatable = false)
	private Weapons type;
	
	@Column(name = "count", nullable = false)
	private int count;
	
	@Column(name = "sided", nullable = false)
	private int sided;
	
	@Column(name = "bonus", nullable = false)
	private int bonus; // 주사위 굴림 외에 + 수치
	
	@Column(name = "accuracy", nullable = false)
	private int accuracy; // 기본 명중률
	
	@Builder
	public Weapon(Weapons type, String name, int count, int sided, int bonus, int accuracy, ItemGrade grade, String description, boolean isGetable) {
		super(name, description, isGetable, grade);
		this.type = type;
		this.count = count;
		this.sided = sided;
		this.bonus = bonus;
		this.accuracy = accuracy;
	}
	
	public DiceRule getDiceRule() {
		return new DiceRule(count, sided);
	}
}
