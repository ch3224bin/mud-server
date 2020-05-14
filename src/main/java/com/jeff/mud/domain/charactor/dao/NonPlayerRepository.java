package com.jeff.mud.domain.charactor.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.domain.charactor.domain.NonPlayer;
import com.jeff.mud.domain.room.domain.Room;

public interface NonPlayerRepository extends JpaRepository<NonPlayer, Long> {
	List<NonPlayer> findByRoom(Room room);
}
