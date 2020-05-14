package com.jeff.mud.domain.charactor.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.context.ApplicationEventPublisher;

import com.jeff.mud.domain.charactor.event.StatusChangeEvent;
import com.jeff.mud.domain.stat.rule.StatRuleBook;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Status {
	
	@Transient
    private ApplicationEventPublisher applicationEventPublisher;
	
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
	
	public Status(ApplicationEventPublisher applicationEventPublisher) {
    	this.applicationEventPublisher = applicationEventPublisher;
    }
	
	public int getMaxHp() {
		return StatRuleBook.getMaxHp(charactor);
	}
	
	public int getMaxMp() {
		return StatRuleBook.getMaxMp(charactor);
	}
	
	public void increaseHp(int val) {
		this.hp += val; // TODO RULE에서 처리
		applicationEventPublisher.publishEvent(new StatusChangeEvent(this));
	}
	
	public void decreaseHp(int val) {
		this.hp -= val; // TODO 상태 변화와 연결됨
	}
}
