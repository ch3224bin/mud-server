package com.jeff.mud.domain.room.domain;

import com.jeff.mud.domain.room.constants.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DoorTest {

  @DisplayName("두개의 출구를 하나의 문으로 연결한다")
  @Test
  void testLink() {
    Room westRoom = Room.builder().id(1L).summary("Test1").description("Test1").build();
    Wayout eastWayout = westRoom.createWayout(Direction.EAST);
    Room eastRoom = Room.builder().id(2L).summary("Test2").description("Test2").build();
    Wayout westWayout = eastRoom.createWayout(Direction.WEST);

    Door door = new Door();
    door.link(eastWayout, westWayout);

    assertThat(eastWayout.getDoor()).isEqualTo(door);
    assertThat(westWayout.getDoor()).isEqualTo(door);
    assertThat(eastWayout.getNextRoom()).isEqualTo(eastRoom);
    assertThat(westWayout.getNextRoom()).isEqualTo(westRoom);
  }
}
