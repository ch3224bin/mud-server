package com.jeff.mud.domain.charactor.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.charactor.domain.QPlayer;
import com.jeff.mud.domain.room.domain.Room;

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
	
	@Override
	public List<Player> findByRoomNotExistsMe(Room room, Player me) {
		final QPlayer player = QPlayer.player;
		return from(player)
			.join(player.room)
			.where(player.room.eq(room)
					.and(player.notIn(me)))
			.fetch();
	}
	
	@Override
	public List<Player> findByRoomAndOnlineAndNotExistsMe(Room room, Player me) {
		final QPlayer player = QPlayer.player;
		return from(player)
				.join(player.room)
				.where(player.room.eq(room)
						.and(player.isOnline.eq(true))
						.and(player.notIn(me)))
				.fetch();
	}
	
	
}
