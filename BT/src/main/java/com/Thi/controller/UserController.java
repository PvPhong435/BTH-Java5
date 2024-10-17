package com.Thi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Thi.entity.User;

@Controller
public class UserController {

	@GetMapping("/Thi")
	public String getBaiThi(Model model)
	{
		User user=new User();
		List<User> listUser=new ArrayList<User>();
		model.addAttribute("user", user);
		model.addAttribute("userList", listUser);
		return "USER";
	}
}
