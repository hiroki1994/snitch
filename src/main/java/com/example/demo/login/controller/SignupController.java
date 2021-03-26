package com.example.demo.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.SecurityConfig;
import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.model.UserForm;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public String showSignUpPage(@ModelAttribute UserForm form, Model model) {

		return "signup/signup";
	}

	@PostMapping("/signupUser")
	public String create(@ModelAttribute @Validated(GroupOrder.class) UserForm form, BindingResult bindingResult, Model model, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		if (bindingResult.hasErrors()) {
			return showSignUpPage(form, model);
		}

		System.out.println(form);

		User user = new User();

		user.setUserName(form.getUserName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());

		userService.create(user);

		String username = String.valueOf(form.getUserName());
		String password = String.valueOf(form.getPassword());

		SecurityConfig.autoLogin(request, username, password, response);

		return null;
	}
}
