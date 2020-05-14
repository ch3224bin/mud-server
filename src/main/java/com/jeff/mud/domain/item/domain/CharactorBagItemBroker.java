package com.jeff.mud.domain.item.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.jeff.mud.domain.charactor.domain.CharactorBag;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_broker_charactor_bag")
@DiscriminatorValue("charactor_bag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CharactorBagItemBroker extends ItemBroker {
	@ManyToOne
	@JoinColumn(name = "charactor_bag_id", nullable = false)
	private CharactorBag charactorBag;
	
	public CharactorBagItemBroker(Item item, CharactorBag charactorBag) {
		super(item);
		this.charactorBag = charactorBag;
	}
}
