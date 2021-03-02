package com.example.demo.login.domain.model;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.login.domain.service.UserService;

public class UniqueUserNameValid implements ConstraintValidator<UniqueUserName, String> {

	@Autowired
	UserService userService;

	@Autowired
	HttpServletRequest request;

	public void initialize(UniqueUserName constraintAnnotation) {


	}

	public boolean isValid(String userName, ConstraintValidatorContext context) {

		if(userName.equals(request.getRemoteUser())) {
			return true;
	    }

		int userNameExist = userService.exist(userName);

		if(userNameExist == 0) {
			return true;
		}

		return false;
	}
}
