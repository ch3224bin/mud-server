package com.jeff.mud.domain.item.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.jeff.mud.command.get.model.Getable;
import com.jeff.mud.domain.player.domain.Player;

import lombok.Getter;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
public class Item implements Getable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;

	@Override
	public void moveTo(Player player) {
		// TODO item_room에서 삭제, item_container에서도 삭제, item_player에 입력
	}
}
