package com.jeff.mud.domain.player.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.jeff.mud.domain.player.domain.Player;
import com.jeff.mud.domain.player.domain.QPlayer;

public class PlayerCustomRepositoryImpl extends QuerydslRepositorySupport implements PlayerCustomRepository {
	public PlayerCustomRepositoryImpl() {
		super(Player.class);
	}

	@Override
	public Optional<Player> findByUsername(String username) {
		final QPlayer player = QPlayer.player;
		Player result = from(player)
				.join(player.account)
				.where(player.account.username.eq(username))
				.fetchOne();
		return Optional.ofNullable(result);
	}
	
	
}
