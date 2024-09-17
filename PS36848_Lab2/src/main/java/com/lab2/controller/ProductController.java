package com.lab2.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab2.model.Product;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("product")
public class ProductController {
	@GetMapping("form")
	public String form() {
		return "formProduct";
	}
	
	@PostMapping("save")
	public String save(@ModelAttribute Product product,Model model) {
		//TODO: process POST request
		model.addAttribute("name",product.getName());
		model.addAttribute("price",product.getPrice());
		
		return "formProduct";
	}
	
	@GetMapping("form2")
	public String form2(Model model) {
		Product p=new Product();
		p.setName("Iphone 12 pro");
		p.setPrice(12000.0);
		return "formProduct2";
	}
	
	@PostMapping("save2")
	public String save2(@ModelAttribute Product product,Model model) {
		//TODO: process POST request
		model.addAttribute("name",product.getName());
		model.addAttribute("price",product.getPrice());
		
		return "formProduct2";
	}
	
	@ModelAttribute("products")
	public List<Product> getItems() {
		return Arrays.asList(new Product("Iphone 11 pro max", 50000.0), new Product("Realme 9", 6000.0));
		}
	
	
}
