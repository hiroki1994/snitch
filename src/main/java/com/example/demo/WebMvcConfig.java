package com.example.demo;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registory) {

		registory.addInterceptor(new CustomHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/css/","/webjars/**","/../../css","/image/");
	}
}