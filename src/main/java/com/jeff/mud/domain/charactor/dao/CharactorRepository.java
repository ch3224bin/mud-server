package com.jeff.mud.domain.charactor.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.room.domain.Room;

public interface CharactorRepository<T extends Charactor> extends JpaRepository<T, Long> {
	Optional<T> findByName(String name);
	List<T> findByRoom(Room room);
}
