package com.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ParameterController {
	@RequestMapping("/sayHello")
	public String welcomeMessage(Model model, HttpServletRequest request)
	{
		String name = request.getParameter("name");
		model.addAttribute("welcomeMessage", "Welcome " + name + " to my web application!");
		return "sayHello";
	}
	
}
