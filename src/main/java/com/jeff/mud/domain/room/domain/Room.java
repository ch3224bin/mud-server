package com.jeff.mud.domain.room.domain;

import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import com.jeff.mud.domain.room.constants.Direction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @AllArgsConstructor @Builder @EqualsAndHashCode(of = "id")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "summary", nullable = false)
	private String summary;
	
	@Column(name = "description", nullable = false)
	private String description;

	@Embedded
	@Builder.Default
	private Wayouts wayouts = new Wayouts();
	
	public List<Wayout> getSortedWayouts() {
		return wayouts.getSortedWayouts();
	}
	
	public String getExitString() {
		return wayouts.getExitString();
	}
	
	public Optional<Wayout> getWayoutByDirection(Direction direction) {
		return wayouts.getWayoutByDirection(direction);
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Wayout createWayout(Room nextRoom, Direction direction) {
		Wayout wayout = create(nextRoom, direction);
		addWayout(wayout);
		return wayout;
	}

	private void addWayout(Wayout wayout) {
		wayouts.add(wayout);
	}

	private Wayout create(Room nextRoom, Direction direction) {
		return Wayout.builder()
			.direction(direction)
			.room(this)
			.nextRoom(nextRoom)
			.build();
	}

	public void linkAnotherRoom(Room anotherRoom, Direction myWay, Direction yourWay) {
		this.createWayout(anotherRoom, myWay)
			.linkAnotherRoom(anotherRoom, yourWay);
	}
}
