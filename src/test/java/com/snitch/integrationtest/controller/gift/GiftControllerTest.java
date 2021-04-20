package com.snitch.integrationtest.controller.gift;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GiftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void displayGiftDetailPage_success() throws Exception {

	int giftId = 1000;

	mockMvc.perform(get("/gifts/" + giftId)
		.param("giftId", "1000"))
		.andExpect(status().isOk())
		.andExpect(view().name("gift_detail/gift_detail"));
    }

    @Test
    public void displayGiftDetailPage_fail_giftIdDoesNotExist() throws Exception {

	int giftId = 9999;

	mockMvc.perform(get("/gifts/" + giftId)
		.param("giftId", "9999"))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"))
		.andExpect(content().string(containsString("指定されたページは存在しません")));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void displayGiftDetailPage_addFavButton() throws Exception {

	int giftId = 1004;

	mockMvc.perform(get("/gifts/" + giftId)
		.param("giftId", "1004"))
		.andExpect(status().isOk())
		.andExpect(view().name("gift_detail/gift_detail"))
		.andExpect(content().string(containsString("お気に入り")));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void displayGiftDetailPage_removeFavoriteButton() throws Exception {

	int giftId = 1000;

	mockMvc.perform(get("/gifts/" + giftId)
		.param("giftId", "1000"))
		.andExpect(status().isOk())
		.andExpect(view().name("gift_detail/gift_detail"))
		.andExpect(content().string(containsString("お気に入り解除")));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void displayGifts_addFavButton_disabledUser() throws Exception {

	int giftId = 1004;

	mockMvc.perform(get("/gifts/" + giftId)
			.param("giftId", "1004"))
			.andExpect(status().isOk())
			.andExpect(view()
			.name("gift_detail/gift_detail"))
			.andExpect(content().string(containsString("お気に入り")));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void displayGifts_fail_disabledGift() throws Exception {

	int giftId = 1031;

	mockMvc.perform(get("/gifts/" + giftId)
		.param("giftId", "1031"))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"))
		.andExpect(content().string(containsString("指定されたページは存在しません")));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void displayGifts_fail_disabledRecommender() throws Exception {

	int giftId = 1032;

	mockMvc.perform(get("/gifts/" + giftId)
		.param("giftId", "1032"))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"))
		.andExpect(content().string(containsString("指定されたページは存在しません")));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void displayGifts_fail_disabledGift_disabledRecommender() throws Exception {

	int giftId = 1033;

	mockMvc.perform(get("/gifts/" + giftId)
		.param("giftId", "1033"))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"))
		.andExpect(content().string(containsString("指定されたページは存在しません")));
    }
}