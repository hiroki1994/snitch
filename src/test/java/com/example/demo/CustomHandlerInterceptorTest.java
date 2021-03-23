package com.example.demo;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.login.domain.model.SearchForm;



@SpringBootTest
@AutoConfigureMockMvc
public class CustomHandlerInterceptorTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void addSearchFormObject() throws Exception {

		SearchForm searchForm = new SearchForm();

		mockMvc.perform(get("/home"))
			.andExpect(model().attribute("searchForm", searchForm));
	}
}
