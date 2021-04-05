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
public class WithdrawControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "userName3")
	public void showDeletePage() throws Exception {

		mockMvc.perform(post("/mypage/deleteUser")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("本当に退会してもよろしいでしょうか?")));
	}

	@Test
	@WithMockUser(username = "userName3")
	public void deleteUser_success() throws Exception {

		mockMvc.perform(delete("/users")
				.with(csrf()))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/login"));
	}

	@Test
	@WithMockUser(username = "userName5")
	public void deleteUser_fail_userNameDoesNotExist() throws Exception {

		mockMvc.perform(delete("/users")
				.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(view().name("error"));
	}
}
