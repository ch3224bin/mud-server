package com.jeff.mud.domain.room.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jeff.mud.domain.room.constants.AreaType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "area")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @AllArgsConstructor @Builder @EqualsAndHashCode(of = "id")
public class Area {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	// 인스턴스인지, 그냥 오픈맵인지 타입
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private AreaType type;
	
	@OneToMany
	@JoinTable(name = "area_room",
			joinColumns = @JoinColumn(name = "area_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "room_id", nullable =  false, unique = true))
	private List<Room> rooms;
}
