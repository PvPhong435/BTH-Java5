package com.lab5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab5.dao.ProductDAO;
import com.lab5.entity.Product;

@Controller
public class ProductController {
	
	@Autowired
	ProductDAO productDao;

	@GetMapping("/product")
	public String getProductList(Model model)
	{
		List<Product> productList=productDao.findAll();
		
		System.out.println();
		model.addAttribute("products", productList);
		return "product";
	}
	
	@RequestMapping("/productDetail/{id}")
	public String GetProductDetail(Model model,@PathVariable("id") int id)
	{
		
		return "productDetail";
	}
}
