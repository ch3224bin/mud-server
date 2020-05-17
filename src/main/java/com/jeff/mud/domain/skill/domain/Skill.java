package com.jeff.mud.domain.skill.domain;

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
import com.jeff.mud.domain.skill.constants.Skills;
import com.jeff.mud.global.domain.model.Typeable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "skill")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Skill implements Typeable<Skills> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "charactor_id", nullable = false, updatable = false)
	private Charactor charactor;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false, updatable = false)
	private Skills type;
	
	@Column(name = "point", nullable = false)
	private int point;
	
	public void increasePoint(int point) {
		this.point += point;
		this.point = Math.min(this.point, 99);
	}
}
