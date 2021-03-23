package com.example.demo.unittest;


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
public class AboutSnitchControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void サイト説明画面() throws Exception {

		mockMvc.perform(get("/aboutsnitch"))
			.andExpect(status().isOk())
			.andExpect(view().name("about/aboutSnitch"))
			.andExpect(content().string(containsString("Snitchとは?")));
	}
}
