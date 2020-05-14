package com.jeff.mud.domain.charactor.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.global.account.domain.Account;

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerCustomRepository {
	Optional<Player> findByAccount(Account account);
	Optional<Player> findByName(String name);
	List<Player> findByRoom(Room room);
}
