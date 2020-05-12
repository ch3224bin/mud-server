package com.jeff.mud.domain.item.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.domain.ItemBroker;

public interface ItemBrokerRepository extends JpaRepository<ItemBroker, Long> {
	Optional<ItemBroker> findByItem(Item item);
}
