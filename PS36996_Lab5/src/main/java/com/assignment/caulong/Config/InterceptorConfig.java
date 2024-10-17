package com.assignment.caulong.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	private AuthenticationInterceptor authnInter;
	private AuthorizationInterceptor authzInter;
	
	public InterceptorConfig(AuthenticationInterceptor authnInter, AuthorizationInterceptor authzInter) {
		super();
		this.authnInter = authnInter;
		this.authzInter = authzInter;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(authnInter).addPathPatterns("/order/**","/admin/**","/order/**","/product/create","/report").excludePathPatterns("/","/login");
		registry.addInterceptor(authzInter).addPathPatterns("/admin/**","/user/**","/order/**","/product/create","/report").excludePathPatterns("/","/login");
	}
}
