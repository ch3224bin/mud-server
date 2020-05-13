package com.jeff.mud.template;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TemplateDc {
	private String player;
	private String container;
	private String item;
	private String adverb;
	private String direction;
	private String words;
}
