package com.snitch.unittest.controller.about;

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
public class AboutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void displayAboutPage() throws Exception {

	mockMvc.perform(get("/about"))
		.andExpect(status().isOk())
		.andExpect(view().name("about/about"))
		.andExpect(content().string(containsString("Snitchとは?")));
    }
}