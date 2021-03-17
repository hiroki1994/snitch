package com.example.demo;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
import com.example.demo.login.domain.model.ValidGroup1;
import com.example.demo.login.domain.model.ValidGroup2;

@SpringBootTest
@AutoConfigureMockMvc
public class SignupFormTest {

	private Validator validator;

	@BeforeEach
	public void init() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	@Sql({"/test_schema.sql", "/test_data.sql"})
	public void 登録情報投入取得_バリデーションエラーなし() throws Exception {

		SignupForm signupForm = new SignupForm();

		String userName = "testUser10";
		String mailAddress = "test@gmail.com";
		String password = "testpassword10";

		signupForm.setUserName(userName);
		signupForm.setMailAddress(mailAddress);
		signupForm.setPassword(password);

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

		constraintValidation.forEach(result -> {

			String propertyPath = result.getPropertyPath().toString();

			if(propertyPath.equals("userName")){
				assertThat(result.getInvalidValue(), is(""));
			}else if(propertyPath.equals("mailAddress")){
				assertThat(result.getInvalidValue(), is(""));
			}else if(propertyPath.equals("password")){
				assertThat(result.getInvalidValue(), is(""));
			}
		});
	}

	@Test
	public void 登録情報投入_バリデーションエラー_文字数() throws Exception {

		SignupForm signupForm = new SignupForm();

		signupForm.setUserName("aa");
		signupForm.setMailAddress("test@gmail.com");
		signupForm.setPassword("cc");

		Set<ConstraintViolation<SignupForm>> constraintValidation = validator.validate(signupForm, ValidGroup2.class);

		assertThat(constraintValidation.size(), is(2));

		constraintValidation.forEach(result -> {

			String propertyPath = result.getPropertyPath().toString();

			if(propertyPath.equals("userName")){
				assertThat(result.getInvalidValue(), is("aa"));
			}else if(propertyPath.equals("password")){
				assertThat(result.getInvalidValue(), is("cc"));
			}
		});
	}

	@Test
	public void 登録情報投入_バリデーションエラー_パターン() throws Exception {

		SignupForm signupForm = new SignupForm();

		signupForm.setUserName("あああ");
		signupForm.setMailAddress("test@gmail.com");
		signupForm.setPassword("いいい");

		Set<ConstraintViolation<SignupForm>> constraintValidation = validator.validate(signupForm, ValidGroup2.class);

		assertThat(constraintValidation.size(), is(2));

		constraintValidation.forEach(result -> {

			String propertyPath = result.getPropertyPath().toString();

			if(propertyPath.equals("userName")){
				assertThat(result.getInvalidValue(), is("あああ"));
			}else if(propertyPath.equals("password")){
				assertThat(result.getInvalidValue(), is("いいい"));
			}
		});
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
}
