package com.jeff.mud.global.account.domain;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "username", unique = true, nullable = false)
	private String username;

	@JsonIgnore @Transient
	private String password;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "provider", nullable = false)
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	@ManyToMany
	@JoinTable(name = "account_role",
		joinColumns = @JoinColumn(name = "account_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;

	@Builder
	public Account(long id, String username, String email, AuthProvider provider) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.provider = provider;
	}
}
