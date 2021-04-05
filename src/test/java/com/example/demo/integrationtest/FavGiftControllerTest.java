package com.example.demo.integrationtest;


import static org.hamcrest.CoreMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@AutoConfigureMockMvc
public class FavGiftControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username="userName3")
	public void createFavGift_success() throws Exception {

		mockMvc.perform(post("/favGift")
			.param("giftId", "1002")
			.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/giftDetail/" + 1002));
	}

	@Test
	@WithMockUser(username="userName3")
	public void createFavGift_fail_giftIdDoesNotExist() throws Exception {

		mockMvc.perform(post("/favGift")
			.param("giftId", "9999")
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("error"));
	}

	@Test
	@WithMockUser(username="userName5")
	public void createFavGift_fail_userNameDoesNotExist() throws Exception {

		mockMvc.perform(post("/favGift")
			.param("giftId", "1000")
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("error"));
	}

	@Test
	@WithMockUser(username="userName5")
	public void createFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		mockMvc.perform(post("/favGift")
				.param("giftId", "1000")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("error"));
	}

	@Test
	@WithMockUser(username="userName3")
	public void deleteFavGift_success() throws Exception {

		mockMvc.perform(delete("/notFavGift")
			.param("giftId", "1001")
			.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/giftDetail/" + 1001));
	}

	@Test
	@WithMockUser(username="userName3")
	public void deleteFavGift_fail_giftIdDoesNotExist() throws Exception {

		mockMvc.perform(delete("/notFavGift")
			.param("giftId", "9999")
			.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/giftDetail/" + 9999));
	}

	@Test
	@WithMockUser(username="userName3")
	public void deleteFavGift_fail_giftIsNotAddedToFavGift() throws Exception {

		mockMvc.perform(delete("/notFavGift")
				.param("giftId", "1002")
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/giftDetail/" + 1002));
	}

	@Test
	@WithMockUser(username="userName5")
	public void deleteFavGift_fail_userNameDoesNotExist() throws Exception {

		mockMvc.perform(delete("/favGift")
				.param("giftId", "1000")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("error"));
	}

	@Test
	public void deleteFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

		mockMvc.perform(delete("/favGift")
				.param("giftId", "9999")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("error"));
	}

	@Test
	@WithMockUser(username = "userName3")
	public void showFavoriteList_success() throws Exception {

		mockMvc.perform(get("/mypage/favorite")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("mypage/favorite/favorite"))
				.andExpect(content().string(containsString("お気に入り")))
				.andExpect(content().string(containsString("2件")))
				.andExpect(content().string(containsString("マカロン")));
	}

	@Test
	@WithMockUser(username = "userName4")
	public void showFavoriteList_success_noFavGift() throws Exception {

		mockMvc.perform(get("/mypage/favorite")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("mypage/favorite/favorite"))
				.andExpect(content().string(containsString("お気に入り")))
				.andExpect(content().string(containsString("0件")));
	}

	@Test
	@WithMockUser(username = "userName5")
	public void showFavoriteList_fail_userNameDoesNotExist() throws Exception {

		mockMvc.perform(get("/mypage/favorite")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("error"));
	}
}
