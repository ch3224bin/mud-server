package com.jeff.mud.domain.stat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.charactor.domain.Charactor;
import com.jeff.mud.domain.stat.constants.StatConstatns;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "charactor_id", nullable = false, updatable = false)
	private Charactor charactor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false, updatable = false)
	private StatConstatns type;
	
	@Column(name = "value", nullable = false)
	private int value = 0;
	
	public String name() {
		return this.type.getName();
	}
}
