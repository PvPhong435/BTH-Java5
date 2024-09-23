package com.java5.slide4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java5.slide4.services.ShoppingCartService;
import com.java5.slide4.util.SP;

@Controller
@RequestMapping("item")
public class ItemController {
	@Autowired
	ShoppingCartService cart;
	
	@RequestMapping("index")
	public String cart(Model model) {
		model.addAttribute("items", SP.item.values());
		model.addAttribute("count", cart.getCount());
		return "cart/spview";
	}
}
