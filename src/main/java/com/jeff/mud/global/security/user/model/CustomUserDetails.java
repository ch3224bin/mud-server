package com.jeff.mud.global.security.user.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jeff.mud.global.account.domain.Account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = -5823552894041314080L;
	
	private String username;
	private String password;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	private Collection<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(Account account) {
		this.username = account.getUsername();
		this.password = account.getPassword();
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.enabled = true;
		
		this.authorities = account.getRoles().stream()
			.map(role -> new SimpleGrantedAuthority(role.getRole()))
			.collect(Collectors.toList());
	}
}
