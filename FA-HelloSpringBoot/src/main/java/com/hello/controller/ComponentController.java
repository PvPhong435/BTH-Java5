package com.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.hello.model.Student;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ComponentController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession session;
	@Autowired
	ServletContext application;
	@RequestMapping("/component")
	public String component(Model model) {
		Student student = new Student("PS12345", "Nguyen Van", "An");
		student.setFirstName("Binh");
		model.addAttribute("student", student);
		int age = 3;
		if(request.getParameter("age") != null)
			age = Integer.parseInt(request.getParameter("age"));
		model.addAttribute("age", age);
		session.setAttribute("username", "java5");
		application.setAttribute("shopName", "FPT Polytechnic Shop");
		return "component";
	}

}
