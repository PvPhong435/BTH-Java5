package com.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class showLogin {
	@RequestMapping("/showlogin")
	public String showlogintest(Model model) {
		model.addAttribute("user", "Pham Van Phong ");
		model.addAttribute("pass","pvp435@123");
		return "showlogin";
	}
	
}
