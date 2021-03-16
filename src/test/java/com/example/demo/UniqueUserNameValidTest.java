package com.example.demo;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.example.demo.login.domain.model.UniqueUserNameValid;
import com.example.demo.login.domain.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UniqueUserNameValidTest {

	@Mock
	private UserService userService;


	@InjectMocks
	private UniqueUserNameValid uniqueUserNameValid;


	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Sql({"/test_schema.sql", "/test_data.sql"})
	public void ユーザー名バリデーション通過() throws Exception {

		String userName = "testUser10";

		when(userService.exist(userName)).thenReturn(0);

		assertThat(uniqueUserNameValid.isValid(userName, null), is(true));
	}

	@Test
	@Sql({"/test_schema.sql", "/test_data.sql"})
	public void ユーザーユニークエラー() throws Exception {

		String userName = "userName3";

		when(userService.exist(userName)).thenReturn(1);

		assertThat(uniqueUserNameValid.isValid(userName, null), is(false));
	}
}
