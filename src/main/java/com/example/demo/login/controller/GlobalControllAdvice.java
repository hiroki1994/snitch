package com.example.demo.login.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.login.domain.model.SearchForm;

@ControllerAdvice
@Component
public class GlobalControllAdvice {


	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessExceptionHandler(EmptyResultDataAccessException e, Model model, HttpServletRequest request) {

		if(request.getAttribute("searchForm")!=null) {
			model.addAttribute((SearchForm)request.getAttribute("searchForm"));
		}else{
			model.addAttribute("searchForm", new SearchForm());
		}

		model.addAttribute("message", "指定されたページは存在しません");

		return "error";
	}

	@ExceptionHandler(IOException.class)
	public String IOExceptionExceptionHandler(IOException e, Model model, HttpServletRequest request) {

		if(request.getAttribute("searchForm")!=null) {
			model.addAttribute((SearchForm)request.getAttribute("searchForm"));
		}else{
			model.addAttribute("searchForm", new SearchForm());
		}

		model.addAttribute("message", "指定されたページは存在しません");

		return "error";
	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model, HttpServletRequest request) {

		if(request.getAttribute("searchForm")!=null) {
			model.addAttribute((SearchForm)request.getAttribute("searchForm"));
		}else{
			model.addAttribute("searchForm", new SearchForm());
		}

		model.addAttribute("message", "指定されたページは存在しません");

		return "error";
	}
}
