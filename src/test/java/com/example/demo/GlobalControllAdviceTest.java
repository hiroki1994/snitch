package com.example.demo;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Sql({"/Delete.sql", "/Schema.sql", "/Insert.sql"})
public class GlobalControllAdviceTest {

    @Autowired
	private MockMvc mockMvc;

    @Test
	public void catchEmptyResultDataAccessException() throws Exception {

		int giftId = 9999;

		mockMvc.perform(get("/giftDetail/" + giftId)
			   .param("giftId", "9999"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("error"))
			   .andExpect(content().string(containsString("指定されたページは存在しません")));
	}

	@Test
	public void catchException() throws Exception {

		String giftId = "H#4kこ";

		mockMvc.perform(get("/giftDetail/" + giftId)
			   .param("giftId", "H#4kこ"))
			   .andExpect(status().isOk())
			   .andExpect(view().name("error"))
			   .andExpect(content().string(containsString("指定されたページは存在しません")));
	}
}
