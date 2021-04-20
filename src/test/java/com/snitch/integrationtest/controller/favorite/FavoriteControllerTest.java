package com.snitch.integrationtest.controller.favorite;

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
    public void createOneFavorite_success() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1002")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 1002));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void createOneFavorite_fail_giftIdDoesNotExist() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "9999")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void createOneFavorite_fail_userNameDoesNotExist() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1000")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void createOneFavorite_fail_userNameAndUserIdDoesNotExist() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1000")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void deleteOneFavorite_success() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1001")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 1001));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void createOneFavorite_fail_disabledUser() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1004")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName6")
    public void createOneFavorite_fail_disabledGift() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1031")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName6")
    public void createOneFavorite_fail_disabledRecommeder() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1032")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName6")
    public void createOneFavorite_fail_disabledGift_disabledRecommeder() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1033")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void createOneFavorite_fail_disabledUser_disabledRecommeder() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1032")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void createOneFavorite_fail_disabledUser_disabledGift_disabledRecommeder() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "1033")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void deleteOneFavorite_fail_giftIdDoesNotExist() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "9999")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 9999));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void deleteOneFavorite_fail_giftIsNotAddedToFavorite() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1002")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 1002));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void deleteOneFavorite_fail_userNameDoesNotExist() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1000")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void deleteOneFavorite_fail_userNameAndGiftIdDoesNotExist() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "9999")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }
    @Test
    @WithMockUser(username = "disabledUser")
    public void deleteOneFavorite_fail_disabledUser() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1000")
		.with(csrf())).andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName4")
    public void deleteOneFavorite_fail_disabledGift() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1031")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 1031));
    }

    @Test
    @WithMockUser(username = "userName4")
    public void deleteOneFavorite_fail_disabledRecommender() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1032")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 1032));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void deleteOneFavorite_fail_disabledUser_disabledGift() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1031")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName4")
    public void deleteONeFavorite_fail_disabledGift_disabledRecommender() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1033")
		.with(csrf()))
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/gifts/" + 1033));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void deleteOneFavorite_fail_disabledUser_disabledRecommender() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1032")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void deleteOneFavorite_fail_disabledUser_disabledGift_disabledRecommender() throws Exception {

	mockMvc.perform(delete("/favorites")
		.param("giftId", "1033")
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
    @WithMockUser(username = "userName6")
    public void displayAllFavorites_success_noFavorite() throws Exception {

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

    @Test
    @WithMockUser(username = "userName3")
    public void displayFavoriteList_success() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("mypage/favorite/favorite"))
		.andExpect(content().string(containsString("お気に入り")))
		.andExpect(content().string(containsString("2件")))
		.andExpect(content().string(containsString("マカロン")));
    }

    @Test
    @WithMockUser(username = "userName6")
    public void displayFavoriteList_success_noFavorites() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("mypage/favorite/favorite"))
		.andExpect(content().string(containsString("お気に入り")))
		.andExpect(content().string(containsString("0件")));
    }

    @Test
    @WithMockUser(username = "userName5")
    public void displayFavoriteList_fail_userNameDoesNotExist() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void displayFavoriteList_fail_disabledUser() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName4")
    public void displayFavoriteList_fail_disabledGift() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("mypage/favorite/favorite"))
		.andExpect(content().string(containsString("お気に入り")))
		.andExpect(content().string(containsString("0件")));
    }

    @Test
    @WithMockUser(username = "userName4")
    public void displayFavoriteList_fail_disabledRecommeder() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("mypage/favorite/favorite"))
		.andExpect(content().string(containsString("お気に入り")))
		.andExpect(content().string(containsString("0件")));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void displayFavoriteList_fail_disabledUser_disabledGift() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "userName4")
    public void displayFavoriteList_fail_disabledGift_disabledRecommeder() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("mypage/favorite/favorite"))
		.andExpect(content().string(containsString("お気に入り")))
		.andExpect(content().string(containsString("0件")));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void displayFavoriteList_fail_disabledUser_disabledRecommeder() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    @WithMockUser(username = "disabledUser")
    public void displayFavoriteList_fail_disabledUser_disabledGift_disabledRecommeder() throws Exception {

	mockMvc.perform(get("/favorites")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }
}