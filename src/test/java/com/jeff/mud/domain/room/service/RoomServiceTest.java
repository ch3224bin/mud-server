package com.jeff.mud.domain.room.service;

import com.jeff.mud.domain.item.service.RoomItemBrokerService;
import com.jeff.mud.domain.room.constants.Direction;
import com.jeff.mud.domain.room.dao.RoomRepository;
import com.jeff.mud.domain.room.domain.Room;
import com.jeff.mud.domain.room.domain.Wayout;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

public class RoomServiceTest {
  @Test
  void testLinkAnotherRoom() {
    // Given
    RoomItemBrokerService roomItemBrokerService = Mockito.mock(RoomItemBrokerService.class);
    RoomRepository roomRepository = Mockito.mock(RoomRepository.class);

    Mockito.when(roomRepository.findById(anyLong())).thenAnswer((invocation) ->
      Optional.of(Room.builder().id(invocation.getArgument(0)).build()));

    RoomService service = new RoomService(roomItemBrokerService, roomRepository);

    // When
    List<Room> rooms = service.linkAnotherRoom(1L, 2L, Direction.EAST, Direction.WEST);

    // Then
    assertThat(rooms).hasSize(2);
    Wayout wo1 = rooms.get(0).getWayoutByDirection(Direction.EAST).get();
    Wayout wo2 = rooms.get(1).getWayoutByDirection(Direction.WEST).get();
    assertThat(wo1.getNextRoom()).isEqualTo(wo2.getRoom());
    assertThat(wo2.getNextRoom()).isEqualTo(wo1.getRoom());
    assertThat(wo1.getDirection()).isEqualTo(Direction.EAST);
    assertThat(wo2.getDirection()).isEqualTo(Direction.WEST);
  }
}
