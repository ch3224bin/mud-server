package com.jeff.mud.domain.stat.dto;

import java.util.ArrayList;
import java.util.List;

import com.jeff.mud.domain.charactor.domain.Player;
import com.jeff.mud.domain.stat.domain.Stat;
import com.jeff.mud.domain.stat.rule.StatRuleBook;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class StatTemplateDc {
	private String name;
	private List<StatDc> stats = new ArrayList<>();
	private int idea;
	private int luck;
	private int know;
	
	public StatTemplateDc(Player player) {
		this.name = player.getName();
		for (Stat stat : player.getStats()) {
			this.stats.add(new StatDc(stat));
			this.idea = Math.max(StatRuleBook.getIdea(stat), this.idea);
			this.luck = Math.max(StatRuleBook.getLuck(stat), this.luck);
			this.know = Math.max(StatRuleBook.getKnow(stat), this.know);
		}
	}
}
