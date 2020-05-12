package com.jeff.mud.domain.item.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.domain.RoomItemBroker;
import com.jeff.mud.domain.room.domain.Room;

public interface RoomItemBrokerRepository extends JpaRepository<RoomItemBroker, Long> {
	Optional<RoomItemBroker> findByItem(Item item);
	List<RoomItemBroker> findByRoom(Room room);
}
