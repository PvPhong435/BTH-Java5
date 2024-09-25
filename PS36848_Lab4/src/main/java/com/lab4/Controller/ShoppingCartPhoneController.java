package com.lab4.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab4.service.ShoppingCartService;


@Controller
public class ShoppingCartPhoneController {

	@Autowired
	ShoppingCartService cart; // tiêm Spring Bean đã tạo
	
	
	@RequestMapping("phone/view")
	public String view(Model model) {
		model.addAttribute("cart", cart);
		model.addAttribute("Count", cart.getCount());
		model.addAttribute("Amount", cart.getAmount());
		return "cart";
	}

	@RequestMapping("/add/{id}")
	public String add(@PathVariable("id") Integer id) {
		cart.add(id);
		return "redirect:/view"; // hiển thị giỏ hàng
	}

	@RequestMapping("/remove/{id}")
	public String remove(@PathVariable("id") Integer id) {
		cart.remove(id);
		return "redirect:/view";
	}

	@RequestMapping("/update/{id}/{pre}")
	public String update(@PathVariable("id") Integer id,@PathVariable("pre") String pre) {
		cart.update(id, pre);
		return "redirect:/view";
	}

	@RequestMapping("/clear")
	public String clear() {
		cart.clear();
		return "redirect:/view";
	}
	
	@RequestMapping("/gotoPay")
	public String pay() {
		
		return "pay";
	}
	
	
}
