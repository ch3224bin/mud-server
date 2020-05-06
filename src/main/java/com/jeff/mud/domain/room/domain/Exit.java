package com.jeff.mud.domain.room.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.room.constants.Direction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exit")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Exit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "room_id", nullable = false)
	private long roomId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "direction", nullable = false)
	private Direction direction;
	
	@OneToOne
    @JoinColumn(name = "next_room_id", nullable = false)
	private Room nextRoom;
	
	@Column(name = "is_show", nullable = false)
	private boolean isShow = true;
	
	@Column(name = "is_locked", nullable = false)
	private boolean isLocked = false;
}
