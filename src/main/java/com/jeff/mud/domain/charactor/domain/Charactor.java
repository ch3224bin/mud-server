package com.jeff.mud.domain.charactor.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.state.CharactorState;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "charactor")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Charactor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false)
	private CharactorState state;
	
	@OneToOne
	@JoinTable(name = "charactor_room",
		joinColumns = @JoinColumn(name = "charactor_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "room_id", nullable = false))
	private Room room;
	
	@OneToOne(mappedBy = "charactor")
	private CharactorBag charactorBag;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void changeState(CharactorState state) {
		this.state = state;
	}

	public void moveTo(Room nextRoom) {
		this.room = nextRoom;
	}
}
