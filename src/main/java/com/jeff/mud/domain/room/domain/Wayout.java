package com.jeff.mud.domain.room.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.room.constants.Direction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wayout")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Wayout implements Comparable<Wayout> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "direction", nullable = false)
	private Direction direction;
	
	@OneToOne
    @JoinColumn(name = "next_room_id", nullable = false)
	private Room nextRoom;
	
	@ManyToOne
	@JoinColumn(name = "door_id", nullable = false)
	private Door door;
	
	@Column(name = "is_show", nullable = false, columnDefinition = "boolean default true")
	private boolean isShow = true;
	
	@Override
	public int compareTo(Wayout o) {
		return Integer.compare(this.direction.ordinal(), o.direction.ordinal());
	}
	
	public String toString() {
		return direction.getName() + (this.getDoor().isLocked() ? "(잠김)" : "");
	}
	
}
