package com.jeff.mud.domain.charactor.dao;

import java.util.Optional;

import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.global.account.domain.Account;

public interface PlayerRepository extends CharactorRepository<Player>, PlayerCustomRepository {
	Optional<Player> findByAccount(Account account);
}
