package com.jeff.mud.domain.room.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.jeff.mud.command.see.model.Seeable;
import com.jeff.mud.domain.item.dto.ItemDc;
import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.template.Template;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomDc implements Seeable {
	private long id;
	private String summary;
	private String description;
	private List<WayoutDc> wayouts;
	private String exitString;
	private List<ItemDc> items;
	
	public RoomDc(Room room) {
		this.id = room.getId();
		this.summary = room.getSummary();
		this.description = room.getDescription();
		this.wayouts = getSortedWayouts(room);
		this.exitString = room.getExitString();
		this.items = room.getItems().stream()
					.map(ItemDc::new)
					.collect(Collectors.toList());
	}

	private List<WayoutDc> getSortedWayouts(Room room) {
		return room.getSortedWayouts().stream()
			.map(WayoutDc::new)
			.collect(Collectors.toList());
	}

	@Override
	public Template template() {
		return Template.room;
	}
}
