package com.example.demo.controller.user;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.config.security.SecurityConfig;
import com.example.demo.domain.model.user.User;
import com.example.demo.domain.model.user.UserForm;
import com.example.demo.domain.service.FavGiftService;
import com.example.demo.domain.service.UserService;
import com.example.demo.domain.validation.GroupOrder;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    FavGiftService favGiftService;

    @GetMapping("/users/new")
    public String display(@ModelAttribute UserForm form, Model model) {

	return "registration/registration";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute @Validated(GroupOrder.class) UserForm form, BindingResult bindingResult,
	    Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

	if (bindingResult.hasErrors()) {
	    return display(form, model);
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

    @GetMapping("/users/edit")
    public String display(@ModelAttribute UserForm form, Model model, HttpServletRequest request) {

	String userName = request.getRemoteUser();
	User user = userService.select(userName);

	form.setUserName(user.getUserName());
	form.setMailAddress(user.getMailAddress());
	model.addAttribute("userForm", form);

	return "mypage/edit_user/edit_user";
    }

    @PutMapping("/users")
    public String update(@ModelAttribute @Validated(GroupOrder.class) UserForm form, BindingResult bindingResult,
	    Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

	if (bindingResult.hasErrors()) {
	    return display(form, model, request);
	}

	User user = new User();
	user.setUserName(form.getUserName());
	user.setMailAddress(form.getMailAddress());
	user.setPassword(form.getPassword());

	String userName = request.getRemoteUser();
	userService.update(user, userName);

	String newUsername = String.valueOf(form.getUserName());
	String newPassword = String.valueOf(form.getPassword());
	SecurityConfig.autoLogin(request, newUsername, newPassword, response);

	return null;
    }

    @GetMapping("/users/withdrawal")
    public String display() {
	return "mypage/withdrawal/withdrawal";
    }

    @DeleteMapping("/users")
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

	String userName = request.getRemoteUser();
	favGiftService.deleteMany(userName);
	userService.delete(userName);
	SecurityConfig.autoLogout(request, response);
    }
}