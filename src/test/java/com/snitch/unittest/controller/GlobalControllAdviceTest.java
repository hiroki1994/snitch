package com.snitch.unittest.controller;

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

@SpringBootTest
@AutoConfigureMockMvc
public class GlobalControllAdviceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void catchEmptyResultDataAccessException() throws Exception {

	int giftId = 9999;

	mockMvc.perform(get("/gifts/" + giftId)
		.param("giftId", "9999"))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"))
		.andExpect(content().string(containsString("指定されたページは存在しません")));
    }

    @Test
    @WithMockUser(username = "userName3")
    public void catchDataIntegrityViolationException() throws Exception {

	mockMvc.perform(post("/favorites")
		.param("giftId", "9999")
		.with(csrf()))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"));
    }

    @Test
    public void catchException() throws Exception {

	String giftId = "H#4kこ";

	mockMvc.perform(get("/gifts/" + giftId)
		.param("giftId", "H#4kこ"))
		.andExpect(status().isOk())
		.andExpect(view().name("error/error"))
		.andExpect(content().string(containsString("指定されたページは存在しません")));
    }
}