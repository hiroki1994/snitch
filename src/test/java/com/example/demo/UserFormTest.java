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

import com.example.demo.login.domain.model.UserForm;
import com.example.demo.login.domain.model.ValidGroup1;
import com.example.demo.login.domain.model.ValidGroup2;

@SpringBootTest
@AutoConfigureMockMvc
public class UserFormTest {

	private Validator validator;

	@BeforeEach
	public void init() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	@Sql({"/test_schema.sql", "/test_data.sql"})
	public void 登録情報投入取得_バリデーションエラーなし() throws Exception {

		UserForm UserForm = new UserForm();

		String userName = "testUser10";
		String mailAddress = "test@gmail.com";
		String password = "testpassword10";

		UserForm.setUserName(userName);
		UserForm.setMailAddress(mailAddress);
		UserForm.setPassword(password);

		String actualUserName = UserForm.getUserName();
		String actualMailAddress = UserForm.getMailAddress();
		String actualPassword = UserForm.getPassword();

		assertEquals("testUser10", actualUserName);
		assertEquals("test@gmail.com", actualMailAddress);
		assertEquals("testpassword10", actualPassword);

		Set<ConstraintViolation<UserForm>> constraintValidation = validator.validate(UserForm, ValidGroup1.class, ValidGroup2.class);

		assertThat(constraintValidation, is(empty()));
	}

	@Test
	public void 登録情報投入_バリデーションエラー_空白() throws Exception {

		UserForm UserForm = new UserForm();

		UserForm.setUserName("");
		UserForm.setMailAddress("");
		UserForm.setPassword("");

		Set<ConstraintViolation<UserForm>> constraintValidation = validator.validate(UserForm, ValidGroup1.class);

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

		UserForm UserForm = new UserForm();

		UserForm.setUserName("aa");
		UserForm.setMailAddress("test@gmail.com");
		UserForm.setPassword("cc");

		Set<ConstraintViolation<UserForm>> constraintValidation = validator.validate(UserForm, ValidGroup2.class);

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

		UserForm UserForm = new UserForm();

		UserForm.setUserName("あああ");
		UserForm.setMailAddress("test@gmail.com");
		UserForm.setPassword("いいい");

		Set<ConstraintViolation<UserForm>> constraintValidation = validator.validate(UserForm, ValidGroup2.class);

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

		UserForm userForm = new UserForm();

		userForm.setUserName("testUser10");
		userForm.setMailAddress("aa");
		userForm.setPassword("testpassword10");

		Set<ConstraintViolation<UserForm>> constraintValidation = validator.validate(userForm, ValidGroup2.class);

		assertThat(constraintValidation.size(), is(1));

		ConstraintViolation<UserForm> violation = constraintValidation.iterator().next();

		assertThat(violation.getInvalidValue(), is("aa"));
	}
}
