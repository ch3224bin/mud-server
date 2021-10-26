package com.jeff.mud.domain.room.domain;

import com.jeff.mud.domain.room.constants.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WayoutTest {

  @DisplayName("출구에 문을 설치한다")
  @Test
  void testInstallDoor() {
    Wayout wo = new Wayout();
    Door door = new Door();
    wo.installDoor(door);
    assertThat(wo.getDoor()).isEqualTo(door);
  }

  @DisplayName("출구와 출구를 연결한다")
  @Test
  void testLinkAnotherWayout() {
    // Given
    Room room2 = Room.builder().id(2L).build();
    Wayout wo1 = Wayout.builder().id(1L).room(Room.builder().id(1L).build()).nextRoom(room2).build();

    // When
    wo1.linkAnotherRoom(room2, Direction.EAST);

    // Then
    Wayout wo2 = room2.getWayoutByDirection(Direction.EAST).get();
    assertThat(wo1.getDoor()).isNotNull().isEqualTo(wo2.getDoor());
    assertThat(wo1.getNextRoom()).isNotNull().isEqualTo(wo2.getRoom());
    assertThat(wo2.getNextRoom()).isNotNull().isEqualTo(wo1.getRoom());
  }
}
