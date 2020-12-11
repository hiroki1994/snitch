package com.example.demo.login.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//共通エラー表示用コントローラークラス
@ControllerAdvice
@Component
public class GlobalControllAdvice {

	//DataAccessException発生時に例外処理として共通エラーページを表示
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {

		//エラーメッセージをモデルに格納
		model.addAttribute("error", "内部サーバーエラー（DB) :GlobalControllAdvice");

		//エラーメッセージをモデルに格納
		model.addAttribute("message", "DataAccessExceptionが発生しました");

		//サーバーエラーをモデルに格納
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

	//Exception発生時に例外処理として共通エラーページを表示
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {

		//エラーメッセージをモデルに格納
		model.addAttribute("error", "内部サーバーエラー :GlobalControllAdvice");

		//エラーメッセージをモデルに格納
		model.addAttribute("message", "Exceptionが発生しました");

		//サーバーエラーをモデルに格納
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

}
