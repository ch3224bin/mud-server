package com.jeff.mud.domain.item.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.room.domain.Door;

import lombok.Getter;

@Entity
@Table(name = "container")
@DiscriminatorValue("container")
@Getter
public class Container extends Item {
	@OneToOne
	@JoinColumn(name = "door_id", nullable = true)
	private Door door;
	
	public boolean isLocked() {
		if (door == null) return false;
		return door.isLocked();
	}
}
