package com.jeff.mud.domain.player.dao;

import java.util.Optional;

import com.jeff.mud.domain.player.domain.Player;

public interface PlayerCustomRepository {
	Optional<Player> findByUsername(String username);
}
