package com.jeff.mud.global.security.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeff.mud.global.account.dao.AccountRepository;
import com.jeff.mud.global.security.user.model.CustomUserDetails;

@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private AccountRepository accountRepository;
	
	public CustomUserDetailsService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return accountRepository.findByUsername(username)
			.map(CustomUserDetails::new)
			.orElseThrow(() -> new UsernameNotFoundException(username));
	}
}
