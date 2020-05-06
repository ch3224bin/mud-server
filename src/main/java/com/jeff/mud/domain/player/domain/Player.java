package com.jeff.mud.domain.player.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jeff.mud.global.account.domain.Account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Player {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@OneToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	private Account account;
}
