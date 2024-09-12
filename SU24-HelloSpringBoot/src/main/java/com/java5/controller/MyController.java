package com.java5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java5.model.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {
	private static final int MAX_AGE_SECONDS = 60 * 60 * 24; // 24h in seconds
	@Autowired
	ServletContext application;
	@Autowired
	HttpSession session;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;

	@RequestMapping("/sayHello")
	public String sayHello() {
		String fullName = request.getParameter("fullName");
		System.out.println(fullName);
		request.setAttribute("message", "Welcome " + fullName + "!");
		session.setAttribute("age", "20");
		application.setAttribute("courseInfo", "Java 5 SpringBoot Web Application course!");
		Student st = new Student("PS21895", "Nguyen An", "Linh", "0908333777", "cuongto@fe.edu.vn");
		Cookie[] myCookie = request.getCookies();
		String cookieValue = "";
		
		boolean found = false;
		if(myCookie != null)
		for (Cookie c : myCookie) {
			if (c.getName().equals("java5cookie")) {
				cookieValue = c.getValue();
				request.setAttribute("java5Key", cookieValue);
				found = true;
			}
			System.out.println(c.getAttribute("java5Key"));

			System.out.println(c.getValue());
		}
		
		if (!found) {
			Cookie c = createCookie("java5cookie", "Java5 Cookie");
			response.addCookie(c);
		}
		request.setAttribute("java5cookie", cookieValue);
		st.setEmail("anhdtps21895@fpt.edu.vn");
		request.setAttribute("st", st);
		System.out.println(application.getRealPath("/"));
		return "sayHello";
	}

	private Cookie createCookie(String cookieName, String cookieValue) {
		try {
			cookieValue = java.net.URLEncoder.encode(cookieValue, "utf-8");
		} catch (Exception e) {

		}
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setPath("/");
		cookie.setMaxAge(MAX_AGE_SECONDS);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		return cookie;
	}
}
