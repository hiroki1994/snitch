package com.example.demo.integrationtest;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.login.controller.GiftDetailController;
import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.service.GiftService;



@SpringBootTest
@AutoConfigureMockMvc
public class GiftDetailControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	GiftService giftService;

	@InjectMocks
	GiftDetailController giftDetailcontroller;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void お土産詳細画面() throws Exception {

		int giftId = 1000;

		Gift gift = new Gift();

		when(giftService.selectOne(giftId)).thenReturn(gift);

		mockMvc.perform(get("/giftDetail/" + giftId)
			.param("giftId", "1000"))
			.andExpect(status().isOk())
			.andExpect(view().name("giftDetail/giftDetail"));
	}
}