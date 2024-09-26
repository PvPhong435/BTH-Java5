package com.lab4.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab4.service.ShoppingCartService;
import com.lab4.service.ShoppingCartServicePhone;
import com.lab4.util.Phone;
import com.lab4.util.SP;



@Controller
public class PhoneController {
	@Autowired
	ShoppingCartServicePhone cart;
	
	@RequestMapping("/Phone")
	public String cart(Model model) 
	{
		model.addAttribute("phones", Phone.phone.values());
		model.addAttribute("count", cart.getCount());
		return "indexPhone";
	}
}
