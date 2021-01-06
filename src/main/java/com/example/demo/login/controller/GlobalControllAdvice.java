package com.example.demo.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.login.domain.model.SearchForm;

//共通エラー表示用コントローラークラス
@ControllerAdvice
@Component
public class GlobalControllAdvice {

	//DataAccessException発生時に例外処理として共通エラーページを表示
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model, SearchForm searchForm) {

		System.out.println("1");
		//エラーメッセージをモデルに格納
		model.addAttribute("message", "DataAccessExceptionが発生しました");

		return "error";
	}

	//Exception発生時に例外処理として共通エラーページを表示
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model, SearchForm searchForm) {


		System.out.println("2");
		//エラーメッセージをモデルに格納
		model.addAttribute("message", "Exceptionが発生しました");


		return "error";
	}



	@ExceptionHandler(IllegalStateException.class)
	public String illegalStateException(IllegalStateException e, Model model, SearchForm searchForm) {


		System.out.println("3");
		//エラーメッセージをモデルに格納
		model.addAttribute("message", "指定されたページは存在しませんIllegalStateException");


		return "error";
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException e, Model model, SearchForm searchForm) {


		System.out.println("4");
		//エラーメッセージをモデルに格納
		model.addAttribute("message", "指定されたページは存在しませんIllegalArgumentException");


		return "error";
	}



	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String emptyResultDataAccessException(EmptyResultDataAccessException e, Model model, SearchForm searchForm) {


		System.out.println("5");
		//エラーメッセージをモデルに格納
		model.addAttribute("message", "指定されたページは存在しませんEmptyResultDataAccessException");


		return "error";
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public String methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, Model model, SearchForm searchForm) {


		System.out.println("5");
		//エラーメッセージをモデルに格納
		model.addAttribute("message", "指定されたページは存在しませんMethodArgumentTypeMismatchException");


		return "error";
	}
}

