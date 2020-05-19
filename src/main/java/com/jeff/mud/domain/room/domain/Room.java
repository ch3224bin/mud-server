package com.jeff.mud.domain.room.domain;

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
}
