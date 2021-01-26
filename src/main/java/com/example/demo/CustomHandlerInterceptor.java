package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.login.domain.model.SearchForm;


public class CustomHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


		System.out.println(request.getRequestURI());
		//リクエストをハンドラ(コントローラー)によってさらに処理をするので、trueを返す
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(modelAndView != null && !modelAndView.getModel().containsKey("searchForm")) {
            modelAndView.addObject("searchForm", new SearchForm());
        }

	}
}
