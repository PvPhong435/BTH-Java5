package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {
	@RequestMapping("/homepage")
	public String homepage(Model model)
	{
		model.addAttribute("user", "Pham Van Phong ");
		model.addAttribute("pass","pvp435@123");
		return "result";
	}
	
}
