package com.jeff.mud.domain.room.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.server.core.Relation;

import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.charactor.dto.CharactorDc;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.item.dto.ItemDc;
import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.template.Template;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Relation(collectionRelation = "rooms", itemRelation = "room")
public class RoomDc implements Seeable {
	private long id;
	private String summary;
	private String description;
	private List<WayoutDc> wayouts;
	private String exitString;
	private List<ItemDc> items;
	private List<CharactorDc> players;
	
	public RoomDc(Room room) {
		this.id = room.getId();
		this.summary = room.getSummary();
		this.description = room.getDescription();
		this.wayouts = getSortedWayouts(room);
		this.exitString = room.getExitString();
	}
	
	public RoomDc(Room room, List<Item> items) {
		this(room);
		this.items = items.stream()
					.map(ItemDc::new)
					.collect(Collectors.toList());
	}
	
	public RoomDc(Room room, List<Item> items, List<CharactorDc> charactors) {
		this(room, items);
		this.players = charactors;
	}

	private List<WayoutDc> getSortedWayouts(Room room) {
		return room.getSortedWayouts().stream()
			.map(WayoutDc::new)
			.collect(Collectors.toList());
	}
	
	public Room toEntity() {
		return Room.builder()
				.summary(summary)
				.description(description)
				.build();
	}

	@Override
	public Template template() {
		return Template.room;
	}
}
