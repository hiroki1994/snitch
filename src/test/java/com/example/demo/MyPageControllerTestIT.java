package com.example.demo;


import static org.hamcrest.CoreMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@Sql({"/test_schema.sql", "/test_data.sql"})
public class MyPageControllerTestIT {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username="userName3")
	public void お気に入り一覧() throws Exception {

		String userName = "userName3";

		mockMvc.perform(post("/mypage/favorite").param("userName", userName)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("mypage/favorite/favorite"))
			.andExpect(content().string(containsString("お気に入り")))
			.andExpect(content().string(containsString("2件")))
			.andExpect(content().string(containsString("マカロン")));
	}

	@Test
	@WithMockUser(username="userName4")
	public void お気に入り一覧_0件() throws Exception {

		String userName = "userName4";

		mockMvc.perform(post("/mypage/favorite").param("userName", userName)
			.with(csrf()))
			.andExpect(status().isOk())
			.andExpect(view().name("mypage/favorite/favorite"))
			.andExpect(content().string(containsString("お気に入り")))
			.andExpect(content().string(containsString("0件")));
	}

}
