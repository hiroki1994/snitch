package com.example.demo.unittest;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.login.domain.model.UniqueUserNameValid;
import com.example.demo.login.domain.service.UserService;

@SpringBootTest
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
	public void userName_validatedSuccessfully() throws Exception {

		String userName = "testUser10";

		when(request.getRemoteUser()).thenReturn(null);
		when(userService.exist(userName)).thenReturn(0);

		assertThat(uniqueUserNameValid.isValid(userName, null), is(true));
	}

	@Test
	public void userName_UniqueError() throws Exception {

		String userName = "userName3";

		when(request.getRemoteUser()).thenReturn(null);
		when(userService.exist(userName)).thenReturn(1);


		assertThat(uniqueUserNameValid.isValid(userName, null), is(false));
	}


	@Test
	public void Username_EqualToAuthentivatedUserName() throws Exception {

		String userName = "userName3";

		when(request.getRemoteUser()).thenReturn(userName);

		assertThat(uniqueUserNameValid.isValid(userName, null), is(true));
	}
}
