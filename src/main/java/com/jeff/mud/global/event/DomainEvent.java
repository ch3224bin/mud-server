package com.jeff.mud.global.event;

public interface DomainEvent<R> {
	R getSource();
}
