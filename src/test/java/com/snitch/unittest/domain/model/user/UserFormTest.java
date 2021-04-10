package com.snitch.unittest.domain.model.user;

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
import org.springframework.boot.test.context.SpringBootTest;

import com.snitch.domain.model.user.UserForm;
import com.snitch.domain.validation.ValidGroup1;
import com.snitch.domain.validation.ValidGroup2;

@SpringBootTest
public class UserFormTest {

    private Validator validator;

    @BeforeEach
    public void setup() {
	ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	validator = factory.getValidator();
    }

    @Test
    public void setGetUserForm() throws Exception {

	String userName = "testUser10";
	String mailAddress = "test@gmail.com";
	String password = "testpassword10";

	UserForm UserForm = new UserForm();
	UserForm.setUserName(userName);
	UserForm.setMailAddress(mailAddress);
	UserForm.setPassword(password);

	String actualUserName = UserForm.getUserName();
	String actualMailAddress = UserForm.getMailAddress();
	String actualPassword = UserForm.getPassword();

	assertEquals("testUser10", actualUserName);
	assertEquals("test@gmail.com", actualMailAddress);
	assertEquals("testpassword10", actualPassword);
    }

    @Test
    public void setUserForm_validatedSuccessfully() throws Exception {

	UserForm UserForm = new UserForm();
	UserForm.setUserName("testUser10");
	UserForm.setMailAddress("test@gmail.com");
	UserForm.setPassword("testpassword10");

	Set<ConstraintViolation<UserForm>> constraintValidation = validator.validate(UserForm, ValidGroup1.class, ValidGroup2.class);

	assertThat(constraintValidation, is(empty()));
    }

    @Test
    public void setUserForm_error_blank() throws Exception {

	UserForm UserForm = new UserForm();
	UserForm.setUserName("");
	UserForm.setMailAddress("");
	UserForm.setPassword("");

	Set<ConstraintViolation<UserForm>> constraintValidation = validator.validate(UserForm, ValidGroup1.class);

	assertThat(constraintValidation.size(), is(3));

	constraintValidation.forEach(result -> {
	    String propertyPath = result.getPropertyPath().toString();

	    if (propertyPath.equals("userName")) {
		assertThat(result.getInvalidValue(), is(""));
	    } else if (propertyPath.equals("mailAddress")) {
		assertThat(result.getInvalidValue(), is(""));
	    } else if (propertyPath.equals("password")) {
		assertThat(result.getInvalidValue(), is(""));
	    }
	});
    }

    @Test
    public void setUserForm_error_length() throws Exception {

	UserForm UserForm = new UserForm();
	UserForm.setUserName("aa");
	UserForm.setMailAddress("test@gmail.com");
	UserForm.setPassword("cc");

	Set<ConstraintViolation<UserForm>> constraintValidation = validator.validate(UserForm, ValidGroup2.class);

	assertThat(constraintValidation.size(), is(2));

	constraintValidation.forEach(result -> {
	    String propertyPath = result.getPropertyPath().toString();

	    if (propertyPath.equals("userName")) {
		assertThat(result.getInvalidValue(), is("aa"));
	    } else if (propertyPath.equals("password")) {
		assertThat(result.getInvalidValue(), is("cc"));
	    }
	});
    }

    @Test
    public void setUserForm_error_pattern() throws Exception {

	UserForm UserForm = new UserForm();
	UserForm.setUserName("あああ");
	UserForm.setMailAddress("test@gmail.com");
	UserForm.setPassword("いいい");

	Set<ConstraintViolation<UserForm>> constraintValidation = validator.validate(UserForm, ValidGroup2.class);

	assertThat(constraintValidation.size(), is(2));

	constraintValidation.forEach(result -> {
	    String propertyPath = result.getPropertyPath().toString();

	    if (propertyPath.equals("userName")) {
		assertThat(result.getInvalidValue(), is("あああ"));
	    } else if (propertyPath.equals("password")) {
		assertThat(result.getInvalidValue(), is("いいい"));
	    }
	});
    }

    @Test
    public void setUserForm_error_email() throws Exception {

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