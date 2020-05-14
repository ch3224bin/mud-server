package com.jeff.mud.domain.item.service;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.jeff.mud.domain.charactor.domain.CharactorBag;
import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.item.dao.ItemBrokerRepository;
import com.jeff.mud.domain.item.domain.CharactorBagItemBroker;
import com.jeff.mud.domain.item.domain.Container;
import com.jeff.mud.domain.item.domain.ContainerItemBroker;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.domain.ItemBroker;
import com.jeff.mud.domain.item.domain.RoomItemBroker;

@Component
public class ItemBrokerService {
	
	private final ItemBrokerRepository itemBrokerRepository;
	
	public ItemBrokerService(ItemBrokerRepository itemBrokerRepository) {
		this.itemBrokerRepository = itemBrokerRepository;
	}

	/**
	 * item을 Player에게 이동
	 * 
	 * @param item
	 * @param player
	 */
	public void moveToPlayer(Item item, Player player) {
		// 예외적인 경우가 아니면 무조건 있다. 
		Optional<ItemBroker> itemBroker = itemBrokerRepository.findByItem(item);
		itemBrokerRepository.delete(itemBroker.get());
		itemBrokerRepository.flush();
		
		CharactorBag playerBag = player.getCharactorBag();
		CharactorBagItemBroker playerBagIteBroker = new CharactorBagItemBroker(item, playerBag);
		itemBrokerRepository.save(playerBagIteBroker);
		playerBag.addItemBroker(playerBagIteBroker);
	}

	public void moveToRoom(Item item, Player player) {
		removeItemFromPlayerBag(player, item);
		
		RoomItemBroker roomItemBroker = new RoomItemBroker(item, player.getRoom());
		itemBrokerRepository.save(roomItemBroker);
	}

	public void moveToContainer(Player player, Container container, Item item) {
		removeItemFromPlayerBag(player, item);
		
		ContainerItemBroker containerItemBroker = new ContainerItemBroker(item, container);
		itemBrokerRepository.save(containerItemBroker);
	}

	private void removeItemFromPlayerBag(Player player, Item item) {
		Optional<CharactorBagItemBroker> itemBroker = player.getCharactorBag().getItemBroker(item);
		player.getCharactorBag().removeItemBroker(itemBroker.get());
		itemBrokerRepository.delete(itemBroker.get());
		itemBrokerRepository.flush();
	}
}
