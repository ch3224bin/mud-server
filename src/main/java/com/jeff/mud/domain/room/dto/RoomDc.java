package com.jeff.mud.domain.room.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.jeff.mud.domain.room.domain.Room;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomDc {
	private long id;
	private String summary;
	private String description;
	private List<ExitDc> exits;
	private String exitString;
	
	public RoomDc(Room room) {
		this.id = room.getId();
		this.summary = room.getSummary();
		this.description = room.getDescription();
		this.exits = room.getExits().stream()
			.map(ExitDc::new)
			.sorted()
			.collect(Collectors.toList());
		this.exitString = exits.stream()
			.filter(x -> x.isShow())
			.map(x -> x.toString())
			.collect(Collectors.joining(" "));
	}
}
