package com.example.demo;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.UniqueUserNameValid;
import com.example.demo.login.domain.model.ValidGroup1;
import com.example.demo.login.domain.model.ValidGroup2;

@SpringBootTest
@AutoConfigureMockMvc
public class SignupFormTest {

	private Validator validator;

	private UniqueUserNameValid uniqueUserNameValid;

	@BeforeEach
	public void init() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void 登録情報投入取得() throws Exception {

		SignupForm signupForm = new SignupForm();

		String userName = "testUser10";
		String mailAddress = "test@gmail.com";
		String password = "testpassword10";

		signupForm.setUserName(userName);
		signupForm.setMailAddress(mailAddress);
		signupForm.setPassword(password);

		when(uniqueUserNameValid.isValid(userName, null)).thenReturn(true);


		String actualUserName = signupForm.getUserName();
		String actualMailAddress = signupForm.getMailAddress();
		String actualPassword = signupForm.getPassword();

		assertEquals("testUser10", actualUserName);
		assertEquals("test@gmail.com", actualMailAddress);
		assertEquals("testpassword10", actualPassword);

		Set<ConstraintViolation<SignupForm>> constraintValidation = validator.validate(signupForm, ValidGroup1.class, ValidGroup2.class);

		assertThat(constraintValidation, is(empty()));
	}

	@Test
	public void 登録情報投入_バリデーションエラー_空白() throws Exception {

		SignupForm signupForm = new SignupForm();

		signupForm.setUserName("");
		signupForm.setMailAddress("");
		signupForm.setPassword("");

		Set<ConstraintViolation<SignupForm>> constraintValidation = validator.validate(signupForm, ValidGroup1.class);

		assertThat(constraintValidation.size(), is(3));

		ConstraintViolation<SignupForm> violation = constraintValidation.iterator().next();

		assertThat(violation.getInvalidValue(), is(""));
	}

	@Test
	public void 登録情報投入_バリデーションエラー_文字数() throws Exception {

		SignupForm signupForm = new SignupForm();

		signupForm.setUserName("aa");
		signupForm.setMailAddress("test@gmail.com");
		signupForm.setPassword("cc");

		Set<ConstraintViolation<SignupForm>> constraintValidation = validator.validate(signupForm, ValidGroup2.class);

		assertThat(constraintValidation.size(), is(2));

		ConstraintViolation<SignupForm> violation = constraintValidation.iterator().next();

		assertThat(violation.getInvalidValue(), is("aa"));
		assertThat(violation.getInvalidValue(), is("cc"));
	}

	@Test
	public void 登録情報投入_バリデーションエラー_パターン() throws Exception {

		SignupForm signupForm = new SignupForm();

		signupForm.setUserName("ああ");
		signupForm.setMailAddress("test@gmail.com");
		signupForm.setPassword("いい");

		Set<ConstraintViolation<SignupForm>> constraintValidation = validator.validate(signupForm, ValidGroup2.class);

		assertThat(constraintValidation.size(), is(4));

		ConstraintViolation<SignupForm> violation = constraintValidation.iterator().next();

		assertThat(violation.getInvalidValue(), is("ああ"));
		assertThat(violation.getInvalidValue(), is("いい"));
	}

	@Test
	public void 登録情報投入_バリデーションエラー_メールアドレス() throws Exception {

		SignupForm signupForm = new SignupForm();

		signupForm.setUserName("testUser10");
		signupForm.setMailAddress("aa");
		signupForm.setPassword("testpassword10");

		Set<ConstraintViolation<SignupForm>> constraintValidation = validator.validate(signupForm, ValidGroup2.class);

		assertThat(constraintValidation.size(), is(1));

		ConstraintViolation<SignupForm> violation = constraintValidation.iterator().next();

		assertThat(violation.getInvalidValue(), is("aa"));
	}

	@Test
	@Sql({"/test_schema.sql", "/test_data.sql"})
	public void 登録情報投入_バリデーションエラー_ユーザーユニークエラー() throws Exception {

		SignupForm signupForm = new SignupForm();

		signupForm.setUserName("userName3");
		signupForm.setMailAddress("test@gmail.com");
		signupForm.setPassword("testpassword10");

		Set<ConstraintViolation<SignupForm>> constraintValidation = validator.validate(signupForm, ValidGroup1.class);

		assertThat(constraintValidation.size(), is(1));

		ConstraintViolation<SignupForm> violation = constraintValidation.iterator().next();

		assertThat(violation.getInvalidValue(), is("userName3"));
	}
}
