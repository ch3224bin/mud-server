package com.jeff.mud.domain.item.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.room.domain.Room;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_broker_room")
@DiscriminatorValue("room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomItemBroker extends ItemBroker {
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
	
	public RoomItemBroker(Item item, Room room) {
		super(item);
		this.room = room;
	}
}
