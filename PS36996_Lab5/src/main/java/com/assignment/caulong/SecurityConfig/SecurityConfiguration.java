//package com.assignment.caulong.SecurityConfig;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfiguration {
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		return http.authorizeHttpRequests(
//						auth -> auth.requestMatchers("/","/login","/register").permitAll()
//				).formLogin(
//						l->l.defaultSuccessUrl("/products")
//				).build();
//	}
//}
