package com.example.demo;


import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.login.controller.SearchController;
import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.service.GiftService;



@SpringBootTest
@AutoConfigureMockMvc
public class SearchControllerTestUT {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	GiftService giftService;

	@InjectMocks
	SearchController searchController;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
	}

	@Test
	public void お土産検索成功() throws Exception {

		String keyword = "マカロン";

		List<Gift> selectedGifts = new ArrayList<Gift>();

		Gift gift = new Gift();

		gift.setGuestName(("水川あさみ"));

		selectedGifts.add(gift);

		when(giftService.countByKeyword(keyword)).thenReturn(2);
		when(giftService.search(keyword)).thenReturn(selectedGifts);

		mockMvc.perform(get("/search")
			.param("keyword", keyword))
			.andExpect(status().isOk())
			.andExpect(view().name("searchResult/searchResult"))
			.andExpect(content().string(containsString("2件見つかりました。")))
			.andExpect(content().string(containsString("水川あさみ")));
	}

	@Test
	public void お土産検索バリデーションエラー() throws Exception {

		String keyword = " ";

		mockMvc.perform(get("/search")
			.param("keyword", keyword))
			.andExpect(status().isOk())
			.andExpect(view().name("searchResult/searchResult"))
			.andExpect(content().string(containsString("キーワードを入力してください")));
	}

	@Test
	public void お土産検索該当なし() throws Exception {

		String keyword = "H#4kこ";

		when(giftService.countByKeyword(keyword)).thenReturn(0);

		mockMvc.perform(get("/search")
			.param("keyword", keyword))
			.andExpect(status().isOk())
			.andExpect(view().name("searchResult/searchResult"))
			.andExpect(content().string(containsString("キーワードに該当するお土産はありませんでした。")));
	}
}
