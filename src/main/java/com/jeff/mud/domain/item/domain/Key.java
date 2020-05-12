package com.jeff.mud.domain.item.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.jeff.mud.domain.room.domain.Door;

import lombok.Getter;

@Entity
@Table(name = "keey")
@DiscriminatorValue("key")
@Getter
public class Key extends Item {
	@ManyToMany
	@JoinTable(name = "key_door",
		joinColumns = @JoinColumn(name ="key_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name ="door_id", nullable = false))
	private List<Door> doors;
}
