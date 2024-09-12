package com.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(Model model) {
		return "dashboard";
	}
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("message", "Welcome to Spring MVC! Welcome to Java5 Course!");
		System.out.println("index");
		return "index";
	}
//	@RequestMapping("/")
//	public String homePage(Model model) {
//		model.addAttribute("message", "Welcome to the first Springboot Application!");
//		System.out.println("index");
//		return "index";
//	}
}