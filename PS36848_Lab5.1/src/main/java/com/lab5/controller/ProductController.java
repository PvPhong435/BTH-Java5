package com.lab5.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab5.dao.CategoryDAO;
import com.lab5.dao.ProductDAO;
import com.lab5.dao.ProductImageDao;
import com.lab5.entity.Product;
import com.lab5.entity.ProductImageId;

@Controller
public class ProductController {
	
	@Autowired
	ProductDAO productDao;
	ProductImageDao productImageDao;
	CategoryDAO categoryDao;

	@GetMapping("/productList")
	public String getProductList(Model model)
	{
		model.addAttribute("products", productDao.findAll());
		return "product";
	}
	
	@RequestMapping("/productAdd")
	public String GetProductAdd(Model model)
	{
		Product product=new Product();
		model.addAttribute("product", product);
		model.addAttribute("products", productDao.findAll());
		model.addAttribute("categories", categoryDao.findAll());
		return "productDetail";
	}
	
	@RequestMapping("/productDetail/{id}")
	public String GetProductDetail(@PathVariable("id") int id,Model model)
	{
		Optional<Product> optionalProduct = productDao.findById(id);
		Product product = optionalProduct.orElse(null); // Lấy sản phẩm hoặc null nếu không tồn tại
		model.addAttribute("product", product);
		model.addAttribute("products", productDao.findAll());
		
		
		return "productDetail";
		
	}
	
	@RequestMapping("/productSave")
	public String saveResult(Model model,@ModelAttribute("product") Product product)
	{
		productDao.save(product);
//		Optional<Product> optionalProduct = productDao.findById(product.getId());
//		Product product2 = optionalProduct.orElse(null); // Lấy sản phẩm hoặc null nếu không tồn tại
//		model.addAttribute("product", product2);
//		model.addAttribute("products", productDao.findAll());
		
		return "redirect:/productDetail/"+product.getId();
	}
	
	
	
//	@GetMapping("/productRemove/{id}")
//	public String removeProduct(@PathVariable("id") int id,Model model)
//	{
//		try {
//			productImageDao.deleteByProductId(id);
//			productDao.deleteById(id);
//			System.out.println("Xóa dữ liệu thành công");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			System.out.println("Xóa dữ liệu Thất Bại");
//		}
//		
//		return "redirect:/productList";
//	}
}
