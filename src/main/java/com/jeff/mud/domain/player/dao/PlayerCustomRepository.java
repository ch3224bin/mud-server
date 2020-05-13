package com.jeff.mud.domain.player.dao;

import java.util.List;
import java.util.Optional;

import com.jeff.mud.domain.player.domain.Player;
import com.jeff.mud.domain.room.domain.Room;

public interface PlayerCustomRepository {
	Optional<Player> findByUsername(String username);
	List<Player> findByRoomNotExistsMe(Room room, Player me);
}
