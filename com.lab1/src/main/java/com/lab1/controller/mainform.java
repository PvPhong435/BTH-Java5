package com.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class mainform {
	@RequestMapping("/main")
	public String index(Model model)
	{
		
		return "index";
	}
}
