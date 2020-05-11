package com.jeff.mud.command.get.finder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.jeff.mud.command.CommandDataCarrier;
import com.jeff.mud.command.common.finder.Finder;
import com.jeff.mud.command.get.model.Getable;

public class GetFinder {
	private final List<Finder<Getable>> finders = Arrays.asList(
			new ItemInTheRoomFinder());
	
	public Getable findTarget(CommandDataCarrier input) {
		if (!input.hasTarget()) {
			return null;
		}
		
		return null;
	}
	
	class ItemInTheRoomFinder implements Finder<Getable> {

		@Override
		public Optional<Getable> find(CommandDataCarrier input) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
