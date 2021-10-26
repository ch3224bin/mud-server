package com.jeff.mud.domain.room.exception;

public class RoomNotFoundException extends RuntimeException {
  public RoomNotFoundException(long id) {
    super(String.format("Room ID %d is not found", id));
  }
}
