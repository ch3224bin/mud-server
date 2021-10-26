package com.jeff.mud.domain.room.domain;

import com.jeff.mud.domain.room.constants.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomTest {

  @DisplayName("방에서 동쪽 출구을 생성한다")
  @Test
  public void testCreateEastWayout() {
    testCreateWayout(Direction.EAST);
  }

  @DisplayName("방에서 서쪽 출구을 생성한다")
  @Test
  public void testCreateWestWayout() {
    testCreateWayout(Direction.WEST);
  }

  private void testCreateWayout(Direction direction) {
    Room room = Room.builder().id(1L).summary("Test").description("Test").build();
    Room nextRoom = Room.builder().id(2L).summary("Test").description("Test").build();
    Wayout wayout = room.createWayout(nextRoom, direction);
    assertThat(wayout.getDirection()).isEqualTo(direction);
    assertThat(wayout.getRoom()).isEqualTo(room);
    assertThat(room.getWayouts()).containsOnlyOnce(wayout);
  }

  @DisplayName("방과 방을 연결한다")
  @Test
  void testLinkAnotherRoom() {
    Room room = Room.builder().id(1L).summary("Test").description("Test").build();
    Room nextRoom = Room.builder().id(2L).summary("Test").description("Test").build();
    room.linkAnotherRoom(nextRoom, Direction.EAST, Direction.WEST);
    assertThat(room.getWayoutByDirection(Direction.EAST)).isNotEmpty();
    assertThat(room.getWayoutByDirection(Direction.EAST).get().getNextRoom()).isEqualTo(nextRoom);
  }
}
