package com.jeff.mud.domain.room.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Strings;
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
	
	@OneToMany(mappedBy = "room")
	private List<Wayout> wayouts;
	
	public List<Wayout> getSortedWayouts() {
		return wayouts.stream()
				.sorted()
				.collect(Collectors.toList());
	}
	
	public String getExitString() {
		String exitString = this.wayouts.stream()
				.filter(x -> x.isShow())
				.map(x -> x.toString())
				.collect(Collectors.joining(" "));
		return Strings.isNullOrEmpty(exitString.strip()) ? "없음" : exitString;
	}
	
	public Optional<Wayout> getWayoutByDirection(Direction direction) {
		return this.wayouts.stream()
			.filter(wayout -> wayout.getDirection() == direction)
			.findFirst();
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
		if (this.wayouts == null) {
			this.wayouts = new ArrayList<>();
		}
		this.wayouts.add(wayout);
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
