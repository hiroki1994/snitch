package com.example.demo.login.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.login.domain.model.SearchForm;


//「AboutSnitch」表示用コントローラークラス
@Controller
public class MyErrorController implements ErrorController{


    @RequestMapping("/error")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String error404(Model model, SearchForm searchForm) {

	model.addAttribute("message", "指定されたページは存在しません");

	return "error";

	}

	@Override
    public String getErrorPath() {
    return "";
	}


	@ExceptionHandler(DataAccessException.class)
	public String DataAccessExceptionHandler(DataAccessException e, Model model, SearchForm searchForm) {

		//エラーメッセージをキー「message」に格納
		model.addAttribute("message", "SignupControllerでDataAccessExceptionが発生しました。入力されたログインIDは既に使用されている可能性があります。");


		return "error";
	}
}
