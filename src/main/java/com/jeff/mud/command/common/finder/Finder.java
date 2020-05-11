package com.jeff.mud.command.common.finder;

import java.util.Optional;

import com.jeff.mud.command.CommandDataCarrier;

public interface Finder <E> {
	Optional<E> find(CommandDataCarrier input);
}
