package com.jeff.mud.domain.room.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "door")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Door {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "is_locked", nullable = false, columnDefinition = "boolean default false")
	private boolean isLocked = false;

	public void unlock() {
		this.isLocked = false;
	}

	public void lock() {
		this.isLocked = true;
	}

	public void link(Wayout wo1, Wayout wo2) {
		wo1.installDoor(this);
		wo2.installDoor(this);
		wo1.setNextRoom(wo2.getRoom());
		wo2.setNextRoom(wo1.getRoom());
	}
}
