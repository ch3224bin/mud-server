package com.jeff.mud.domain.room.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	@WithMockUser(username = "user")
	public void test_paging() throws Exception {
		mockMvc.perform(get("/rooms/")
				.param("page", "0")
				.param("size", "5")
				.param("sort", "id"))
		.andDo(print())
		.andExpect(status().isOk());
	}
}
