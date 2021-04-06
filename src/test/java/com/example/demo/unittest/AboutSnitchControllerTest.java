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
	public void showAboutPage() throws Exception {

		mockMvc.perform(get("/about-snitch-page"))
				.andExpect(status().isOk())
				.andExpect(view().name("about/about_snitch"))
				.andExpect(content().string(containsString("Snitchとは?")));
	}
}
