package com.lab5.controller;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ScrollPosition.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab5.dao.StudentDAO;
import com.lab5.entity.Category;
import com.lab5.entity.Student;

@Controller
public class TestDbController {

	@Autowired
	StudentDAO dao;
	
	@RequestMapping("/index")
	public String getRequest(Model model)
	{	
		Sort sort=Sort.by("Name");
		List<Student> students=dao.findAll(sort);
		model.addAttribute("students",students);
		return "StudentView";
	}
	
	@RequestMapping("/sort/{col}")
	public String getRequest(Model model,@PathVariable("col") String col)
	{	
		Sort sort=Sort.by(col);
		List<Student> students=dao.findAll(sort);
		model.addAttribute("students",students);
		return "StudentView";
	}
	
	@RequestMapping("/page")
	public String getPage(Model model,@RequestParam("pageNo") Optional<Integer> pageNo)
	{
		org.springframework.data.domain.Pageable pageable=PageRequest.of(pageNo.orElse(0), 2);
		
		Page<Student> pages = dao.findAll(pageable);
		model.addAttribute("students",pages);
		return "StudentView1";
	}
}
