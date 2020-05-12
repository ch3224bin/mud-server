package com.jeff.mud.domain.item.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_broker_container")
@DiscriminatorValue("container")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ContainerItemBroker extends ItemBroker {
	@ManyToOne
	@JoinColumn(name = "container_id", nullable = false)
	private Container container;
	
	public ContainerItemBroker(Item item, Container container) {
		super(item);
		this.container = container;
	}
}
