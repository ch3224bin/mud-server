package com.jeff.mud.domain.room.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.domain.room.domain.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
