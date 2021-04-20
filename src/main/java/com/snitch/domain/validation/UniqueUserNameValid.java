package com.snitch.domain.validation;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.snitch.domain.service.UserService;

public class UniqueUserNameValid implements ConstraintValidator<UniqueUserName, String> {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    public void initialize(UniqueUserName constraintAnnotation) {

    }

    public boolean isValid(String userName, ConstraintValidatorContext context) {

	if (userService == null) {
	    return true;
	}

	if (userName.equals(request.getRemoteUser())) {
	    return true;
	}

	int count = userService.exist(userName);

	if (count == 0) {
	    return true;
	}

	return false;
    }
}