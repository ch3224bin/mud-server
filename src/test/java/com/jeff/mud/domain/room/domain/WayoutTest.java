package com.jeff.mud.domain.room.domain;

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
}
