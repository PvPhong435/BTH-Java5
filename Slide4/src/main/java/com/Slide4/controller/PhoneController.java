package com.Slide4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Slide4.model.Phone;


@Controller
public class PhoneController {
	
	@Autowired
	Phone phone;
	
	@ResponseBody
	@GetMapping("/getPhone")
	public Phone getPhoneInfor()
	{
		
		return phone;
	}
}
