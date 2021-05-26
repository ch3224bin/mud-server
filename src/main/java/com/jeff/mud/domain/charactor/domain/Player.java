package com.jeff.mud.domain.charactor.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.domain.stat.domain.Stat;
import com.jeff.mud.state.CharactorState;
import lombok.Builder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeff.mud.global.account.domain.Account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "player")
@DiscriminatorValue("player")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
// TODO test
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Player extends Charactor {

	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	private Account account;

	@Column(name = "is_online", nullable = false, columnDefinition = "boolean default false")
	private boolean isOnline;

	@Builder
	public Player(String name, CharactorState state, Room room, CharactorBag charactorBag, List<Stat> stats,
								Account account, boolean isOnline) {
		super(name, state, room, charactorBag, stats);
		this.account = account;
		this.isOnline = isOnline;
	}

	public void online() {
		this.isOnline = true;
	}

	public void offline() {
		this.isOnline = false;
	}
}
