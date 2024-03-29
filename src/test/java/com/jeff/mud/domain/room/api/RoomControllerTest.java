package com.jeff.mud.domain.room.api;

import com.jeff.mud.domain.room.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.hateoas.PagedModel;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RoomControllerTest {

  private MockMvc mockMvc;

  private RoomModelAssembler assembler;
  private RoomService roomService;

  @BeforeEach
  void setUp() {
    assembler = Mockito.mock(RoomModelAssembler.class);
    roomService = Mockito.mock(RoomService.class);
    mockMvc = MockMvcBuilders.standaloneSetup(new RoomController(assembler, roomService))
      .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
  }

  @Test
  public void test_paging() throws Exception {
    Mockito.when(roomService.getPagedRooms(any())).thenReturn(Page.empty());
    Mockito.when(assembler.toPagedModel(any())).thenReturn(PagedModel.of(Collections.emptyList(), new PagedModel.PageMetadata(15, 0, 0)));

    mockMvc.perform(get("/rooms/")
      .param("page", "0")
      .param("size", "5")
      .param("sort", "id"))
      .andDo(print())
      .andExpect(status().isOk());
  }
}