package com.jeff.mud.domain.charactor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.stat.rule.StatRuleBook;
import com.jeff.mud.listener.status.AuditingStatusListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status")
@EntityListeners(AuditingStatusListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "charactor_id", nullable = false, updatable = false)
	private Charactor charactor;
	
	@Column(name = "hp")
	private int hp;
	
	@Column(name = "mp")
	private int mp;
	
	@Column(name = "moveRate")
	private int moveRate;
	
	@Column(name = "luck", columnDefinition = "int(3) default 15")
	private int luck;
	
	public int getMaxHp() {
		return StatRuleBook.getMaxHp(charactor);
	}
	
	public int getMaxMp() {
		return StatRuleBook.getMaxMp(charactor);
	}
	
	public int getMaxMoveRate() {
		return StatRuleBook.getMaxMoveRate(charactor);
	}
	
	public void increaseHp(int val) {
		this.hp += val; // TODO RULE에서 처리
	}
	
	public void decreaseHp(int val) {
		this.hp -= val; // TODO 상태 변화와 연결됨
	}
	
	public Status(Charactor charactor) {
		this.charactor = charactor;
		this.hp = getMaxHp();
		this.mp = getMaxMp();
	}
}
