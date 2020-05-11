package com.jeff.mud.domain.room.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.common.base.Strings;
import com.jeff.mud.domain.item.domain.Item;
import com.jeff.mud.domain.room.constants.Direction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "room")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "summary", nullable = false)
	private String summary;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@OneToMany
	@JoinColumn(name = "room_id")
	private List<Wayout> wayouts;
	
	@OneToMany
	@JoinTable(name = "item_room",
			joinColumns = @JoinColumn(name = "room_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "item_id", nullable = false))
	private List<Item> items;
	
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
	
	public Optional<Item> getItem(String itemName) {
		return this.items.stream()
			.filter(item -> {
				String[] names = item.getName().split(" ");
				for (String name : names) {
					if (name.startsWith(itemName)) {
						return true;
					}
				}
				return false;})
			.findFirst();
	}
}
