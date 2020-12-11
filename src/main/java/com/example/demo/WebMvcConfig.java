package com.example.demo;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Interceptorを追加するクラス
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registory) {

		//css、js、画像へのリクエストが発生したとき以外にInterceptorを追加
		registory.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/","/webjars/**","/../../css","/image/");
	}
}