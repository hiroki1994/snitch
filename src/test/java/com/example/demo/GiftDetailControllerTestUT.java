package com.example.demo;


import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.login.controller.GiftDetailController;
import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.service.FavGiftService;
import com.example.demo.login.domain.service.GiftService;



@SpringBootTest
@AutoConfigureMockMvc
public class GiftDetailControllerTestUT {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	GiftService giftService;

	@Mock
	FavGiftService favGiftService;

	@InjectMocks
	GiftDetailController giftDetailController;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(giftDetailController).build();
	}

	@Test
	public void お土産詳細画面() throws Exception {

		int giftId = 1000;

		Gift gift = new Gift();

		String userName = null;

		when(giftService.selectOne(giftId)).thenReturn(gift);
		when(favGiftService.existFavId(userName, giftId)).thenReturn(false);


		mockMvc.perform(get("/giftDetail/" + giftId)
			.param("giftId", "1000"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("giftDetail/giftDetail"));
	}

	@Test
	@WithMockUser(username="userName3")
	public void お土産詳細画面＿お気に入りボタン表示() throws Exception {

		int giftId = 1002;

		Gift gift = new Gift();

		String userName = "userName3";

		when(giftService.selectOne(giftId)).thenReturn(gift);
		when(favGiftService.existFavId(userName, giftId)).thenReturn(false);

		mockMvc.perform(get("/giftDetail/" + giftId)
			.param("giftId", "1002"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("giftDetail/giftDetail"))
			.andExpect(content().string(containsString("お気に入り")));
	}

	@Test
	@WithMockUser(username="userName3")
	public void お土産詳細画面_お気に入り解除ボタン表示() throws Exception {

		int giftId = 1000;

		Gift gift = new Gift();

		String userName = "userName3";

		when(giftService.selectOne(giftId)).thenReturn(gift);
		when(favGiftService.existFavId(userName, giftId)).thenReturn(true);

		mockMvc.perform(get("/giftDetail/" + giftId)
			.param("giftId", "1000"))
		    .andDo(print())
			.andExpect(status().isOk())
			.andExpect(view().name("giftDetail/giftDetail"))
			.andExpect(content().string(containsString("お気に入り解除")));
	}
}