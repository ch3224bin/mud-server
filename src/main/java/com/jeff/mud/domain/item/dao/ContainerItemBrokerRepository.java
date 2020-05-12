package com.jeff.mud.domain.item.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeff.mud.domain.item.domain.Container;
import com.jeff.mud.domain.item.domain.ContainerItemBroker;

public interface ContainerItemBrokerRepository extends JpaRepository<ContainerItemBroker, Long> {
	List<ContainerItemBroker> findByContainer(Container container);
}
