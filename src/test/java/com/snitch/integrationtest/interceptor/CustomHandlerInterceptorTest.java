package com.snitch.integrationtest.interceptor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.snitch.domain.model.searchform.SearchForm;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomHandlerInterceptorTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addSearchForm() throws Exception {

	SearchForm searchForm = new SearchForm();

	mockMvc.perform(get("/")).andExpect(model().attribute("searchForm", searchForm));
    }
}