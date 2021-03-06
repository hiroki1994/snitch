package com.example.demo;


import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.controller.FavGiftController;
import com.example.demo.login.domain.service.FavGiftService;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Sql({"/test_schema.sql", "/test_data.sql"})
public class FavGiftControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	FavGiftService favGiftService;

	@InjectMocks
	FavGiftController favGiftController;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@WithMockUser(username="userName3")
	public void お気に入り追加() throws Exception {

		String userName = "userName3";

		int giftId = 1002;

		boolean result = true;

		when(favGiftService.create(userName, giftId)).thenReturn(result);

		mockMvc.perform(post("/favGift")
			.param("userName", userName)
			.param("giftId", "1002")
			.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/giftDetail/" + giftId));
	}

	@Test
	@WithMockUser(username="userName3")
	public void お気に入り削除() throws Exception {


		String userName = "userName3";

		int giftId = 1001;

		boolean result = true;

		when(favGiftService.delete(userName, giftId)).thenReturn(result);

		mockMvc.perform(post("/notFavGift")
			.param("userName", userName)
			.param("giftId", "1001")
			.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/giftDetail/" + giftId));
	}
}
