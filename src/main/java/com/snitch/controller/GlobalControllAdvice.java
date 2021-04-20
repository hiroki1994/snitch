package com.snitch.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.snitch.domain.model.searchform.SearchForm;

@ControllerAdvice
@Component
public class GlobalControllAdvice {

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String catchEmptyResultDataAccessExceptionHandler(EmptyResultDataAccessException e, Model model,
	    HttpServletRequest request) {

	if (request.getAttribute("searchForm") != null) {
	    model.addAttribute((SearchForm) request.getAttribute("searchForm"));
	} else {
	    model.addAttribute("searchForm", new SearchForm());
	}

	model.addAttribute("message", "指定されたページは存在しません");

	return "error/error";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String catchDataIntegrityViolationExceptionHandler(DataIntegrityViolationException e, Model model,
	    HttpServletRequest request) {

	if (request.getAttribute("searchForm") != null) {
	    model.addAttribute((SearchForm) request.getAttribute("searchForm"));
	} else {
	    model.addAttribute("searchForm", new SearchForm());
	}

	model.addAttribute("message", "指定されたページは存在しません");

	return "error/error";
    }

    @ExceptionHandler(IOException.class)
    public String catchIOExceptionExceptionHandler(IOException e, Model model, HttpServletRequest request) {

	if (request.getAttribute("searchForm") != null) {
	    model.addAttribute((SearchForm) request.getAttribute("searchForm"));
	} else {
	    model.addAttribute("searchForm", new SearchForm());
	}

	model.addAttribute("message", "指定されたページは存在しません");

	return "error/error";
    }

    @ExceptionHandler(Exception.class)
    public String catchExceptionHandler(Exception e, Model model, HttpServletRequest request) {

	if (request.getAttribute("searchForm") != null) {
	    model.addAttribute((SearchForm) request.getAttribute("searchForm"));
	} else {
	    model.addAttribute("searchForm", new SearchForm());
	}

	model.addAttribute("message", "指定されたページは存在しません");

	return "error/error";
    }
}