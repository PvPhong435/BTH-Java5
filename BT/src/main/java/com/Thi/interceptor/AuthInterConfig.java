package com.Thi.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthInterConfig implements WebMvcConfigurer {
	@Autowired
	AuthInterceptor auth;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(auth).addPathPatterns("/account/**","/category/**", "/order/**", "/admin/**")
//				.excludePathPatterns("/assets/**","/account/login");
		registry.addInterceptor(auth).addPathPatterns("/AddOrUpdate","/Remove/**","/Lab6Add","/sendMail")
		.excludePathPatterns("/assets/**","/Lab6Login");
	}
}
