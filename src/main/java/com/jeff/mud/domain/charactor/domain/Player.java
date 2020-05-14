package com.jeff.mud.domain.charactor.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jeff.mud.global.account.domain.Account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
	
	public void online() {
		this.isOnline = true;
	}
	
	public void offline() {
		this.isOnline = false;
	}
}
