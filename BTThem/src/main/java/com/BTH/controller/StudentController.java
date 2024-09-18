package com.BTH.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.BTH.model.Country;
import com.BTH.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class StudentController {
	
	@GetMapping("/StudentForm")
	public String getMethodName(Model model) {
		Student student=new Student();
		model.addAttribute("student", student);
		
		return "StudentForm";
	}
	
	@PostMapping("/saveStudent")
	public String postMethodName(@ModelAttribute("student") Student student,Model model) {
		//TODO: process POST request
		
		return "StudentResult";
	}
	
	@ModelAttribute("country")
	public List<Country> getCountries()
	{
		List<Country> listCountry=new ArrayList<Country>();
		listCountry.add(new Country("VN","Việt Nam"));
		listCountry.add(new Country("USA","Mỹ"));
		listCountry.add(new Country("TQ","Trung Quốc"));
		listCountry.add(new Country("JP","Nhật Bản"));
		return listCountry;
	}
	
	@ModelAttribute("gender")
	public String[] getGender()
	{
		String[] gender= {"Male","Female"};
		return gender;
	}
	
	
}
