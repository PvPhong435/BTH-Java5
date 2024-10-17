package com.assignment.caulong.Config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.assignment.caulong.models.Account;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Account account = (Account) request.getSession().getAttribute("user");
		System.out.println("is Authorized");
		// TODO Auto-generated method stub
		if (account.isAdmin()) {
			String secureURI=(String) request.getSession().getAttribute("secureURI");
			System.out.println(secureURI);
			if(!request.getRequestURI().equals("/admin")||secureURI==null) {
				return true;
			}
			if(request.getRequestURI().equals(secureURI)) {
				return true;
			}
			if(secureURI!=null) {
				String URI=secureURI;
				response.sendRedirect(URI);
				request.setAttribute(secureURI, null);
				return true;
			}
		}
		response.sendRedirect("/error1");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	public AuthorizationInterceptor() {
		// TODO Auto-generated constructor stub
	}

}
