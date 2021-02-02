package com.example.demo.login.domain.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.login.domain.service.UserService;

public class UniqueUserNameValid implements ConstraintValidator<UniqueUserName, String> {

	@Autowired
	UserService userService;

	public void initialize(UniqueUserName constraintAnnotation) {


	}

	public boolean isValid(String userName, ConstraintValidatorContext context) {

		User user = userService.findByUserName(userName);
		if(user == null) {
			return true;
		}
		return false;
	}
}
