package com.example.demo.integrationtest.controller.favorite;

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
public class FavoriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "userName3")
    public void createOneFavGift_success() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1002")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 1002));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void createOneFavGift_fail_giftIdDoesNotExist() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "9999")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void createOneFavGift_fail_userNameDoesNotExist() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1000")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void createOneFavGift_fail_userNameAndUserIdDoesNotExist() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1000")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void deleteOneFavGift_success() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1001")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 1001));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void deleteOneFavGift_fail_giftIdDoesNotExist() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "9999")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 9999));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void deleteOneFavGift_fail_giftIsNotAddedToFavGift() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1002")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 1002));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void deleteOneFavGift_fail_userNameDoesNotExist() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1000")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void deleteOneFavGift_fail_userNameAndGiftIdDoesNotExist() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "9999")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void displayAllFavorites_success() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("mypage/favorite/favorite"))
		.andExpect(content().string(containsString("お気に入り")))
		.andExpect(content().string(containsString("2件")))
		.andExpect(content().string(containsString("マカロン")));
    }

    @Test
    @WithMockUser(username = "userName4")
    public void displayAllFavorites_success_noFavGift() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("mypage/favorite/favorite"))
		.andExpect(content().string(containsString("お気に入り")))
		.andExpect(content().string(containsString("0件")));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void displayAllFavorites_fail_userNameDoesNotExist() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }
}