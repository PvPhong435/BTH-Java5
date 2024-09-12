package com.java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("welcomeMessage", "Welcome to my homepage!");
		return "home";
	} 
}
