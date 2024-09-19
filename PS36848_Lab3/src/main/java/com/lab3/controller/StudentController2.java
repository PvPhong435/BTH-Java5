package com.lab3.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab3.model.Student;
import com.lab3.model.Student3;

import jakarta.validation.Valid;

@Controller
public class StudentController2 {
	@RequestMapping("/student/form2")
	public String form(Model model) {
		Student3 student = new Student3();
		model.addAttribute("sv3", student);
		return "form2";
	}
	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
	    }

	@RequestMapping("/student/save2")
	public String requestMethodName(@ModelAttribute("sv3") @Valid Student3 sv, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("message", "Some fields are not valid. Please fix!");
		} else {
			model.addAttribute("message", "All fields are valid!");
			sv.setName(sv.getName().toUpperCase());
		}
		//model.addAttribute("sv3", sv);
		return "form2";
	}

	@ModelAttribute("genders")
	public Map<Boolean, String> getGenders() {
		Map<Boolean, String> map = new HashMap<Boolean, String>();
		map.put(true, "Male");
		map.put(false, "Female");

		return map;
	}

	@ModelAttribute("faculties")
	public List<String> getFaculties() {
		return Arrays.asList("CNTT", "DLNHKS", "QTDN");
	}

	@ModelAttribute("hobbies")
	public Map<String, String> getHobbies() {
		Map<String, String> map = new HashMap<>();
		map.put("T", "Travelling");
		map.put("M", "Music");
		map.put("F", "Food");
		map.put("O", "Other");
		return map;
	}
}
