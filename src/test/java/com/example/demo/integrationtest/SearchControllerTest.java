package com.example.demo.integrationtest;


import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void searchGift_found() throws Exception {

		String keyword = "マカロン";

		mockMvc.perform(get("/gifts")
			.param("keyword", keyword))
			.andExpect(status().isOk())
			.andExpect(view().name("search_result/search_result"))
			.andExpect(content().string(containsString("2件見つかりました。")))
			.andExpect(content().string(containsString("水川あさみ")));
	}

	@Test
	public void searchGift_notFound() throws Exception {

		String keyword = "H#4kこ";

		mockMvc.perform(get("/gifts")
			.param("keyword", keyword))
			.andExpect(status().isOk())
			.andExpect(view().name("search_result/search_result"))
			.andExpect(content().string(containsString("キーワードに該当するお土産はありませんでした。")));
	}

	@Test
	public void searchGift_validationError() throws Exception {

		String keyword = " ";

		mockMvc.perform(get("/gifts")
			.param("keyword", keyword))
			.andExpect(status().isOk())
			.andExpect(view().name("search_result/search_result"))
			.andExpect(content().string(containsString("キーワードを入力してください")));
	}
}
