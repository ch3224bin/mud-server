package com.jeff.mud.domain.player.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.domain.player.domain.Player;
import com.jeff.mud.global.account.domain.Account;

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerCustomRepository {
	Optional<Player> findByAccount(Account account);
	Optional<Player> findByName(String name);
}
