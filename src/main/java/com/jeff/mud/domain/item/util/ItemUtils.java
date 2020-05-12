package com.jeff.mud.domain.item.util;

import java.util.Optional;
import java.util.stream.Stream;

import com.jeff.mud.domain.item.domain.Item;

public class ItemUtils {
	public static Optional<Item> getItemByName(Stream<Item> stream, String itemName) {
		return stream.filter(item -> {
			String[] names = item.getName().split(" ");
			for (String name : names) {
				if (name.startsWith(itemName)) {
					return true;
				}
			}
			return false;})
		.findFirst();
	}
}
