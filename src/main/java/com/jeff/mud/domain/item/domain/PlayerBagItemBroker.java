package com.jeff.mud.domain.item.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.player.domain.PlayerBag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_broker_player_bag")
@DiscriminatorValue("player_bag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PlayerBagItemBroker extends ItemBroker {
	@ManyToOne
	@JoinColumn(name = "player_bag_id", nullable = false)
	private PlayerBag playerBag;
	
	public PlayerBagItemBroker(Item item, PlayerBag playerBag) {
		super(item);
		this.playerBag = playerBag;
	}
}
