package com.example.demo.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.login.domain.model.SearchForm;

//共通エラー表示用コントローラークラス
@ControllerAdvice
@Component
public class GlobalControllAdvice {

	//DataAccessException発生時に例外処理として共通エラーページを表示
	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String dataAccessExceptionHandler(DataAccessException e, Model model, SearchForm searchForm) {

		System.out.println("なーーーーーーーーーーー");
		model.addAttribute("message", "指定されたページは存在しません");
		return "error";
	}

	//Exception発生時に例外処理として共通エラーページを表示
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String exceptionHandler(Exception e, Model model, SearchForm searchForm) {

		System.out.println("なーーーーーーーーーーー");
		model.addAttribute("message", "指定されたページは存在しません");
		return "error";
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String emptyResultDataAccessException(EmptyResultDataAccessException e, Model model, SearchForm searchForm) {

		System.out.println("なーーーーーーーーーーー");
		return "error";
	}

}
