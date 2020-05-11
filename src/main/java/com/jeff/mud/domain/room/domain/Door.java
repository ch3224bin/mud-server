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
}
