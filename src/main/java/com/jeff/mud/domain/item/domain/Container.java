package com.jeff.mud.domain.item.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "container")
@DiscriminatorValue("container")
@Getter
public class Container extends Item {
	@Column(name = "is_locked", nullable = false, columnDefinition = "boolean default false")
	private boolean isLocked;
}
