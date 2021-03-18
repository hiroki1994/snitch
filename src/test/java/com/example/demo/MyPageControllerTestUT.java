package com.example.demo;


import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.login.controller.MyPageController;
import com.example.demo.login.domain.model.FavGift;
import com.example.demo.login.domain.service.FavGiftService;



@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class MyPageControllerTestUT {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	FavGiftService favGiftService;

	@InjectMocks
	MyPageController myPageController;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(myPageController).build();
	}

	@Test
	@WithMockUser(username="userName3")
	public void お気に入り一覧() throws Exception {

		String userName = "userName3";

		List<FavGift> allFavGifts = new ArrayList<>();

		when(favGiftService.selectAll(userName)).thenReturn(allFavGifts);
		when(favGiftService.count(userName)).thenReturn(2);

		mockMvc.perform(post("/mypage/favorite").param("userName", userName)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("mypage/favorite/favorite"))
			.andExpect(content().string(containsString("お気に入り")));
	}

	@Test
	@WithMockUser(username="userName4")
	public void お気に入り一覧_0件() throws Exception {

		String userName = "userName4";

		List<FavGift> allFavGifts = new ArrayList<>();

		when(favGiftService.selectAll(userName)).thenReturn(allFavGifts);
		when(favGiftService.count(userName)).thenReturn(0);

		mockMvc.perform(post("/mypage/favorite").param("userName", userName)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("mypage/favorite/favorite"))
			.andExpect(content().string(containsString("お気に入り")))
			.andExpect(content().string(containsString("0件")));
	}
}
