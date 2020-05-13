package com.jeff.mud.command.see.finder;

import java.util.List;
import java.util.Optional;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.item.domain.Container;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.dto.ItemDc;
import com.jeff.mud.domain.item.service.ContainerItemBrokerService;
import com.jeff.mud.domain.item.service.RoomItemBrokerService;
import com.jeff.mud.domain.room.domain.Room;

/**
 * 방안의 아이템 찾기
 * 
 * @author ChangHo Vin
 */
@Component
@Order(2)
public class ItemInTheRoomSeeFinder implements Finder<Seeable> {
	
	private final RoomItemBrokerService roomItemBrokerService;
	private final ContainerItemBrokerService containerItemBrokerService;
	
	public ItemInTheRoomSeeFinder(RoomItemBrokerService roomItemBrokerService, ContainerItemBrokerService containerItemBrokerService) {
		this.roomItemBrokerService = roomItemBrokerService;
		this.containerItemBrokerService = containerItemBrokerService;
	}
	
	@Override
	public Optional<Seeable> find(CommandDataCarrier input) {
		Room room = input.getPlayer().getRoom();
		
		// 찾은 놈이 컨테이너인 경우 컨테이너 안의 내용물도 보여줘야함
		ItemDc result = null;
		Optional<Item> item = roomItemBrokerService.getItemByName(room, input.getTarget());
		if (item.isPresent()) {
			if (item.get() instanceof Container) {
				List<Item> items = containerItemBrokerService.getItemsByContainer((Container) item.get());
				result = new ItemDc(item.get(), items);
			} else {
				result = new ItemDc(item.get());
			}
		}
		
		return Optional.ofNullable(result);
	}
}
