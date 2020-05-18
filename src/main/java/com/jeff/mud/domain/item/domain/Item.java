package com.jeff.mud.domain.item.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.jeff.mud.domain.item.constants.ItemGrade;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "is_getable", nullable = false, updatable = false, columnDefinition = "boolean default true")
	private boolean isGetable = true;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "grade", nullable = false)
	private ItemGrade grade;

	public Item(String name, String description, boolean isGetable, ItemGrade grade) {
		this.name = name;
		this.description = description;
		this.isGetable = isGetable;
		this.grade = grade;
	}
}
