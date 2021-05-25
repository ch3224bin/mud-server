package com.jeff.mud.global.account.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.global.account.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByUsername(String username);

  Optional<Account> findByEmail(String email);
}
