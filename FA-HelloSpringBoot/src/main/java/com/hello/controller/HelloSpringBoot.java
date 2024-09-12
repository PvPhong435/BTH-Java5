package com.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloSpringBoot {
	@RequestMapping("/hello")
	public String index(Model model) {
		model.addAttribute("message", "Welcome to Spring MVC! Welcome to Java5 Course!");
		model.addAttribute("courseInfo", "SOF3021 Java 5");
		return "index";
	}

}
