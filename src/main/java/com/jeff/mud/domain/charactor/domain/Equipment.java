package com.jeff.mud.domain.charactor.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.item.constants.Weapons;
import com.jeff.mud.domain.item.domain.Weapon;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "equipment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Equipment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "charactor_id", nullable = false, updatable = false)
	private Charactor charactor;
	
	@OneToOne
	@JoinColumn(name = "weapon_id", nullable = true)
	private Weapon weapon;
	
	public Weapon getWeapon() {
		if (weapon == null) {
			return Weapons.FIST.createWeapon(); // 없으면 맨주먹
		}
		return this.weapon;
	}
}
