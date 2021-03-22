package com.example.demo;


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
public class HomeControllerTestIT {

	@Autowired
	private MockMvc mockMvc;


	@Test
	@Sql({"/test_schema.sql", "/test_data.sql"})
	public void showHome() throws Exception {

		mockMvc.perform(get("/home"))
			.andExpect(status().isOk())
			.andExpect(view().name("home/home"));
	}
}
