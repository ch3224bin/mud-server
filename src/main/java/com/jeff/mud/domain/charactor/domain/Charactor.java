package com.jeff.mud.domain.charactor.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.domain.skill.constants.Skills;
import com.jeff.mud.domain.skill.domain.Skill;
import com.jeff.mud.domain.stat.constants.Stats;
import com.jeff.mud.domain.stat.domain.Stat;
import com.jeff.mud.state.CharactorState;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "charactor")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Charactor {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "state", nullable = false)
	private CharactorState state;
	
	@OneToOne
	@JoinTable(name = "charactor_room",
		joinColumns = @JoinColumn(name = "charactor_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "room_id", nullable = false))
	private Room room;
	
	@OneToOne(mappedBy = "charactor")
	private CharactorBag charactorBag;
	
	@OneToOne(mappedBy = "charactor")
	private Equipment equipment;
	
	@OneToMany(mappedBy = "charactor", fetch = FetchType.EAGER)
	@OrderBy("id asc")
	private List<Stat> stats;
	
	@OneToMany(mappedBy = "charactor")
	@OrderBy("id asc")
	public List<Skill> skills;
	
	@OneToOne(mappedBy = "charactor")
	private Status status;
	
	@Transient
	private Map<Stats, Stat> statMap;
	
	@Transient
	private Map<Skills, Skill> skillMap;
	
	public Charactor(String name, CharactorState state, Room room, CharactorBag charactorBag, List<Stat> stats) {
		this.name = name;
		this.state = state;
		this.room = room;
		this.charactorBag = charactorBag;
		this.stats = stats;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void changeState(CharactorState state) {
		this.state = state;
	}

	public void moveTo(Room nextRoom) {
		this.room = nextRoom;
	}
	
	public Stat getStat(Stats statConstatns) {
		if (statMap == null) {
			statMap = new HashMap<>();
			for (Stat stat : stats) {
				statMap.put(stat.getType(), stat);
			}
		}
		return statMap.get(statConstatns);
	}
	
	public Skill getSkill(Skills skillConstants) {
		if (skillMap == null) {
			skillMap = new HashMap<>();
			for (Skill skill : skills) {
				skillMap.put(skill.getType(), skill);
			}
		}
		return skillMap.get(skillConstants);
	}
}
