package com.jeff.mud.domain.player.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.domain.Key;
import com.jeff.mud.domain.item.domain.PlayerBagItemBroker;
import com.jeff.mud.domain.item.util.ItemUtils;
import com.jeff.mud.domain.room.domain.Door;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "player_bag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PlayerBag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "player_id", nullable = false, updatable = false, unique = true)
	private Player player;
	
	@OneToMany(mappedBy = "playerBag")
	private List<PlayerBagItemBroker> itemBrokers;
	
	public void addItemBroker(PlayerBagItemBroker playerBagItemBroker) {
		itemBrokers.add(playerBagItemBroker);
	}

	public Optional<Item> getItem(String itemName) {
		Stream<Item> stream = itemBrokers.stream()
				.map(PlayerBagItemBroker::getItem);
		return ItemUtils.getItemByName(stream, itemName);
	}

	public boolean hasKey(Door door) {
		return itemBrokers.stream()
			.map(PlayerBagItemBroker::getItem)
			.filter(item -> item instanceof Key)
			.map(item -> (Key)item)
			.anyMatch(key -> {
				for (Door d : key.getDoors()) {
					if (d.getId() == door.getId()) {
						return true;
					}
				}
				return false;
			});
	}

	public Optional<PlayerBagItemBroker> getItemBroker(Item item) {
		return itemBrokers.stream()
			.filter(ib -> ib.getItem().getId() == item.getId())
			.findFirst();
	}

	public void removeItemBroker(PlayerBagItemBroker itemBroker) {
		for (PlayerBagItemBroker ib : itemBrokers) {
			if (ib.getId() == itemBroker.getId()) {
				itemBrokers.remove(ib);
				return;
			}
		}
	}
}
