package com.example.demo.integrationtest;


import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/test_schema.sql", "/test_data.sql"})
public class GiftDetailControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void showGiftDetail_success() throws Exception {

		int giftId = 1000;

		mockMvc.perform(get("/giftDetail/" + giftId)
			.param("giftId", "1000"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("giftDetail/giftDetail"));
	}

	@Test
	public void showGiftDetail_fail_giftIdDoesNotExist() throws Exception {

		int giftId = 9999;

		mockMvc.perform(get("/giftDetail/" + giftId)
			.param("giftId", "9999"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("error"));
	}

	@Test
	@WithMockUser(username="userName3")
	public void showGiftDetail_showAddFavButton() throws Exception {

		int giftId = 1002;

		mockMvc.perform(get("/giftDetail/" + giftId)
			.param("giftId", "1002"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("giftDetail/giftDetail"))
			.andExpect(content().string(containsString("お気に入り")));
	}

	@Test
	@WithMockUser(username="userName3")
	public void showGiftDetail_showRemoveFavButton() throws Exception {

		int giftId = 1000;

		mockMvc.perform(get("/giftDetail/" + giftId)
			.param("giftId", "1000"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("giftDetail/giftDetail"))
			.andExpect(content().string(containsString("お気に入り解除")));
	}
}