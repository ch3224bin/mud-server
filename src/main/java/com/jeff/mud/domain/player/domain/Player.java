package com.jeff.mud.domain.player.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.global.account.domain.Account;
import com.jeff.mud.state.PlayerState;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
// TODO test
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false)
	private PlayerState state;
	
	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	private Account account;
	
	@OneToOne
	@JoinTable(name = "player_room",
		joinColumns = @JoinColumn(name = "player_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "room_id", nullable = false))
	private Room room;
	
	@Builder
	public Player(String name, PlayerState state, Account account) {
		this.name = name;
		this.state = state;
		this.account = account;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void changeState(PlayerState state) {
		this.state = state;
	}

	public void moveTo(Room nextRoom) {
		this.room = nextRoom;
	}
}
