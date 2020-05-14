package com.jeff.mud.domain.charactor.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "non_player")
@DiscriminatorValue("non_player")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NonPlayer extends Charactor {

	@Column(name = "is_attackable")
	private boolean isAttackable;
}
