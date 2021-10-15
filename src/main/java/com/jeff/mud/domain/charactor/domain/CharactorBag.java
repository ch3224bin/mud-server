package com.jeff.mud.domain.charactor.domain;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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
import com.jeff.mud.domain.item.domain.CharactorBagItemBroker;
import com.jeff.mud.domain.item.util.ItemUtils;
import com.jeff.mud.domain.room.domain.Door;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "charactor_bag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CharactorBag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "charactor_id", nullable = false, updatable = false, unique = true)
	private Charactor charactor;
	
	@OneToMany(mappedBy = "charactorBag")
	private List<CharactorBagItemBroker> itemBrokers;
	
	public void addItemBroker(CharactorBagItemBroker charactorBagItemBroker) {
		itemBrokers.add(charactorBagItemBroker);
	}

	public Optional<Item> getItem(String itemName) {
		Stream<Item> stream = itemBrokers.stream()
				.map(CharactorBagItemBroker::getItem);
		return ItemUtils.getItemByName(stream, itemName);
	}

	public boolean hasKey(Door door) {
		return itemBrokers.stream()
			.map(CharactorBagItemBroker::getItem)
			.filter(item -> item instanceof Key)
			.map(item -> (Key) item)
			.anyMatch(isRightKey(door));
	}

	private Predicate<Key> isRightKey(Door door) {
		return key -> {
			for (Door d : key.getDoors()) {
				if (d.getId() == door.getId()) {
					return true;
				}
			}
			return false;
		};
	}

	public Optional<CharactorBagItemBroker> getItemBroker(Item item) {
		return itemBrokers.stream()
			.filter(ib -> ib.getItem().getId() == item.getId())
			.findFirst();
	}

	public void removeItemBroker(CharactorBagItemBroker itemBroker) {
		for (CharactorBagItemBroker ib : itemBrokers) {
			if (ib.getId() == itemBroker.getId()) {
				itemBrokers.remove(ib);
				return;
			}
		}
	}
}
