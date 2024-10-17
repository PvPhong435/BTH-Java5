package com.assignment.caulong.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.caulong.dao.CategoryDAO;
import com.assignment.caulong.dao.ProductDAO;
import com.assignment.caulong.dao.ProductDTO;
import com.assignment.caulong.models.Image;
import com.assignment.caulong.models.Product;
import com.assignment.caulong.services.uploadService;

@Controller
public class ProductController {
	private uploadService upload;
	private ProductDAO productDAO;
	private CategoryDAO categoryDAO;
	public ProductController(uploadService upload,ProductDAO productdao,CategoryDAO categoryDAO) {
		super();
		this.upload=upload;
		this.productDAO = productdao;
		this.categoryDAO= categoryDAO;
	}
	@GetMapping("/products")
	public String getProducts(Model model,@RequestParam(required = false) String sort,@RequestParam(required = false) String key) {
		if(sort==null) {
			sort="";
		}
		List<Product> products=new ArrayList<>();
		if(key==null) {
		switch(sort) {
		case "category":{
			products=productDAO.findAll(Sort.by("category"));
			break;
		}
		case "name":{
			products=productDAO.findAll(Sort.by("name"));
			break;
		}
		case "price":{
			products=productDAO.findAll(Sort.by("price"));
			break;
		}
		default:{
			products=productDAO.findAll(Sort.by("price"));
			break;
		}
		}
		model.addAttribute("products", products);
		return "products";
		};
		products=productDAO.findAllByIgnoreCaseNameContaining(key, Pageable.ofSize(3)).toList();
		model.addAttribute("products", products);
		return "products";
	}
	@GetMapping("/product/{id}")
	public String getProductById(@PathVariable int id,Model model) {
		Product product=productDAO.findById(id).orElse(null);
		if(product!=null) {
			model.addAttribute("product", product);
			return "product";
		}
		return "index";
	}
	@GetMapping("/product/create")
	public String getCreateProductForm(Model model) {
		model.addAttribute("categories", categoryDAO.findAll());
		return "productform";
	}
	@PostMapping("/product/create")
	public String getCreateProductForm(@ModelAttribute ProductDTO productDTO) {
		MultipartFile[] images=productDTO.getImages();
		Product product=new Product();
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setAvailable(true);
		product.setDescription(productDTO.getDescription());
		product.setCategory(categoryDAO.findById(productDTO.getCategory()).orElse(null));
		System.out.println(product.getName());
		System.out.println(product.getCategory().getName());
		String uploadDir="C:\\FPT\\Java5Lab5\\";
		List<Image> imagesList=null;
		try {
			imagesList=upload.uploadTo(images, uploadDir, product);
			if (imagesList == null) {
		        System.out.println("Files array is null");
		    }
			for(Image img:imagesList) {
				product.addImage(img);
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		productDAO.save(product);
		return "redirect:/product/"+product.getId();
	}
	
}
