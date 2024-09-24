package com.lab4.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab4.service.ShoppingCartService;
import com.lab4.util.SP;



@Controller
public class ItemController {
	@Autowired
	ShoppingCartService cart;
	
	@RequestMapping("/")
	public String cart(Model model) 
	{
		model.addAttribute("items", SP.item.values());
		model.addAttribute("count", cart.getCount());
		model.addAttribute("message", "Hello controller");
		System.out.println(SP.item.values());
		System.out.println("Hello");
		
		return "index";
	}
}
