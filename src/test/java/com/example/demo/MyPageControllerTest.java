package com.example.demo;


import static org.hamcrest.CoreMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.login.domain.service.FavGiftService;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MyPageControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	FavGiftService favGiftService;

	@Test
	@Sql({"/Delete.sql","/Schema.sql", "/Insert.sql"})
	public void お気に入り一覧() throws Exception {

		String userName = "userName";

		//List<FavGift> allFavGifts = new ArrayList<>();

		//when(favGiftService.selectAll(userName)).thenReturn(allFavGifts);
		//when(favGiftService.count(userName)).thenReturn(1);

		mockMvc.perform(post("/mypage/favorite").param("userName", userName).with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("mypage/favorite/favorite"))
			.andExpect(content().string(containsString("お気に入り")));
	}

}
