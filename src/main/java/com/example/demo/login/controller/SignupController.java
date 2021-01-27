package com.example.demo.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.SecurityConfig;
import com.example.demo.login.domain.model.GroupOrder;
import com.example.demo.login.domain.model.SignupForm;
import com.example.demo.login.domain.model.User;
import com.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {

	@Autowired
	private UserService userService;

	@PostMapping("/signup")
	public String postSignUp(@ModelAttribute SignupForm form, Model model) {

		return "signup/signup";
	}

	@PostMapping("/signupUser")
	public String postSignUp(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		if (bindingResult.hasErrors()) {
			return postSignUp(form, model);
		}

		System.out.println(form);

		User user = new User();

		user.setUserName(form.getUserName());
		user.setMailAddress(form.getMailAddress());
		user.setPassword(form.getPassword());

		boolean result = userService.insertOne(user);

		if (result == true) {
			System.out.println("insert成功");
		} else {
			System.out.println("insert失敗");
		}

		try {
			String username = String.valueOf(form.getUserName());
			String password = String.valueOf(form.getPassword());
			SecurityConfig.authWithHttpServletRequestLogin(request, username, password, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {

		model.addAttribute("error", "内部サーバーエラー(DB) : ExceptionHandler");

		model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました。入力されたログインIDは既に使用されている可能性があります。");

		model.addAttribute("status" ,HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {

		model.addAttribute("error", "内部サーバーエラー: ExceptionHandler");

		model.addAttribute("message", "SignupControllerでExceptionが発生しました。");

		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

}
