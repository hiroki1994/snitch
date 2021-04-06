package com.example.demo.integrationtest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SecurityConfigTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(context)
			.apply(springSecurity())
			.build();
	}

	@Test
	public void login_success() throws Exception{

		String userName = "userName3";

		String password = "password";

		mockMvc.perform(post("/login")
			.with(csrf())
			.param("userName", userName)
			.param("password", password))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/mypage"));
	}

	@Test
	public void login_fail_userNameDoesNotExist() throws Exception{

		String userName = "userName5";

		String password = "password";

		mockMvc.perform(post("/login")
			.with(csrf())
			.param("userName", userName)
			.param("password", password))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/login-page"));
	}

	@Test
	@WithMockUser(username="userName3")
	public void logout() throws Exception{

		mockMvc.perform(post("/logout")
			.with(csrf()))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/login-page"));
	}
}
