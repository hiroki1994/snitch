package com.example.demo;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.login.domain.model.UniqueUserNameValid;
import com.example.demo.login.domain.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
public class UniqueUserNameValidTest {

	@Mock
	private UserService userService;

	@Mock
	private HttpServletRequest request;;


	@InjectMocks
	private UniqueUserNameValid uniqueUserNameValid;


	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void ユーザー名バリデーション通過() throws Exception {

		String userName = "testUser10";

		when(request.getRemoteUser()).thenReturn(null);
		when(userService.exist(userName)).thenReturn(0);

		assertThat(uniqueUserNameValid.isValid(userName, null), is(true));
	}

	@Test
	public void ユーザーユニークエラー() throws Exception {

		String userName = "userName3";

		when(request.getRemoteUser()).thenReturn(null);
		when(userService.exist(userName)).thenReturn(1);


		assertThat(uniqueUserNameValid.isValid(userName, null), is(false));
	}


	@Test
	public void 認証済みユーザーネームと同名() throws Exception {

		String userName = "userName3";

		when(request.getRemoteUser()).thenReturn(userName);

		assertThat(uniqueUserNameValid.isValid(userName, null), is(true));
	}

	@Test
	public void userServiceNull() throws Exception {

		String userName = "testUser10";
		when(request.getRemoteUser()).thenReturn(null);

		assertThat(uniqueUserNameValid.isValid(userName, null), is(true));
	}
}
