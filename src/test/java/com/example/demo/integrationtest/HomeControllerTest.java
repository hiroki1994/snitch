package com.example.demo.integrationtest;


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

import com.example.demo.login.controller.HomeController;
import com.example.demo.login.domain.model.Gift;
import com.example.demo.login.domain.service.GiftService;



@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	GiftService giftService;

	@InjectMocks
	HomeController homeController;

	@BeforeEach
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void showHomePage() throws Exception {

		List<Gift> selectedGifts = new ArrayList<Gift>();

		when(giftService.selectMany()).thenReturn(selectedGifts);

		mockMvc.perform(get("/home"))
			.andExpect(status().isOk())
			.andExpect(view().name("home/home"));
	}
}
