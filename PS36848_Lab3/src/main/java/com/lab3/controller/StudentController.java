package com.lab3.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab3.model.Student;


@Controller
public class StudentController {
	@RequestMapping("/student/form")
	public String form(Model model) {
		Student student=new Student();
		model.addAttribute("sv", student);
		return "form";
	}
	
	@RequestMapping("/student/save")
	public String requestMethodName(@ModelAttribute("sv")Student sv,Model model) {
		
		model.addAttribute("sv", sv);
		return "form";
	}
	
	@ModelAttribute("genders")
	public Map<Boolean, String> getGenders()
	{
		Map<Boolean, String> map=new HashMap<Boolean, String>();
		map.put(true, "Male");
		map.put(false, "Female");
		
		return map;
	}
	
	@ModelAttribute("faculties")
	public List<String> getFaculties()
	{
		return Arrays.asList("CNTT","DLNHKS","QTDN");
	}
	
	@ModelAttribute("hobbies")
	public Map<String, String> getHobbies(){
	Map<String, String> map = new HashMap<>();
	map.put("T", "Travelling");
	map.put("M", "Music");
	map.put("F", "Food");
	map.put("O", "Other");
	return map;
	}
}
