package com.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.websocket.server.PathParam;


@Controller
public class ParamController {
	@Autowired
	
	
	@RequestMapping("/param/form")
	public String form() {
		return "form";
	}
	
	@RequestMapping("/param/save/{x}")
	public String save(@RequestParam("y") String y,@PathVariable("x") String x,Model model) {
		model.addAttribute("x", x);
		model.addAttribute("y", y);
		return "form";
	}
	
}
