package com.lab5.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lab5.dao.CategoryDAO;
import com.lab5.dao.ProductDAO;
import com.lab5.dao.ProductImageDao;
import com.lab5.entity.Category;
import com.lab5.entity.Product;
import com.lab5.entity.ProductImage;
import com.lab5.entity.ProductImageId;

import jakarta.validation.Valid;
import lombok.val;

@Controller
public class ProductController {
	
	@Autowired
	ProductDAO productDao;
	@Autowired
	ProductImageDao productImageDao;
	@Autowired
	CategoryDAO categoryDao;
	
	Boolean sortChect=false;

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
		
		List<Category> categories = categoryDao.findAll();
		System.out.println("all category"); 
		System.out.println(categories); 
		model.addAttribute("categories", categoryDao.findAll());
		return "productDetail";
	}
	
	@RequestMapping("/productAddNew")
	public String GetProductAddNew(Model model)
	{
		Product product=new Product();
		model.addAttribute("product", product);
		
		List<Category> categories = categoryDao.findAll();
		System.out.println("all category"); 
		System.out.println(categories); 
		model.addAttribute("categories", categoryDao.findAll());
		return "productAdd";
	}
	
	@RequestMapping("/productDetail/{id}")
	public String GetProductDetail(@PathVariable("id") int id,Model model)
	{
		Optional<Product> optionalProduct = productDao.findById(id);
		Product product = optionalProduct.orElse(null); // Lấy sản phẩm hoặc null nếu không tồn tại
		model.addAttribute("product", product);
		model.addAttribute("products", productDao.findAll());
		
		List<Category> categories = categoryDao.findAll();
		System.out.println("all category"); 
		System.out.println(categories); 
		model.addAttribute("categories", categories);
		
		
		return "productDetail";
		
	}
	
	@RequestMapping("/productSave")
	public String saveResult(Model model,@Valid @ModelAttribute("product") Product product,BindingResult result)
	{
		if(result.hasErrors())
		{
			System.out.println("Lỗi khi sửa dữ liệu");
			return "productDetail/"+product.getId();
		}
		else
		{
			productDao.save(product);
			return "redirect:/productDetail/"+product.getId();
		}
//		Optional<Product> optionalProduct = productDao.findById(product.getId());
//		Product product2 = optionalProduct.orElse(null); // Lấy sản phẩm hoặc null nếu không tồn tại
//		model.addAttribute("product", product2);
//		model.addAttribute("products", productDao.findAll());
		
		
	}
	
	
	
	@GetMapping("/productRemove/{id}")
	public String removeProduct(@PathVariable("id") int id,Model model)
	{
		try {
			ProductImageId productImageId=new ProductImageId();
			productImageId.setProductId(id);
			productImageDao.deleteById(productImageId);
			productDao.deleteById(id);
			System.out.println("Xóa dữ liệu thành công");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Xóa dữ liệu Thất Bại");
		}
		
		return "redirect:/productList";
	}
	
	@RequestMapping("/productSaveAdd")
	public String saveProductAdd(Model model, @Valid @ModelAttribute("product") Product product, 
	                              @RequestParam("productImages") MultipartFile[] productImages,BindingResult result) {
	    if(result.hasErrors())
	    {
	    	return "forward:/productAdd";
	    }
	    else
	    {
	    	// Lưu sản phẩm vào cơ sở dữ liệu
		    productDao.save(product);
		    
		    // Kiểm tra xem sản phẩm đã được lưu thành công hay chưa
		    if (productDao.findById(product.getId()) != null) {
		        // Chuyển đổi MultipartFile[] sang List<ProductImage>
		    	List<ProductImage> productImages1 = convertMultipartFilesToProductImages(productImages);
		        product.setProductImages(productImages1); // Gán danh sách ảnh cho sản phẩm

		     

		        // Chuyển hướng đến trang chi tiết sản phẩm
		        return "redirect:/productDetail/" + product.getId();
		    } else {
		        return "product"; // Trả về trang để người dùng có thể chỉnh sửa lại
		    }
	    }
		
	}
	
	public List<ProductImage> convertMultipartFilesToProductImages(MultipartFile[] files) {
	    List<ProductImage> productImages = new ArrayList<>();
	    for (MultipartFile file : files) {
	        ProductImage productImage = new ProductImage();
	        // Giả sử ProductImage có một thuộc tính imageData để lưu trữ dữ liệu hình ảnh
	        productImage.setImageLink(file.getOriginalFilename());
	        productImages.add(productImage);
	    }
	    return productImages;
	}
	
	@RequestMapping("/productSort/{SortBy}")
	public String SortProduct(@PathVariable("SortBy") String SortBy,Model model)
	{
		
		Sort sort= Sort.by(Direction.DESC,SortBy);
		if(sortChect)
		{
			sort = Sort.by(Direction.DESC,SortBy);
			sortChect=!sortChect;
		}
		else
		{
			sort = Sort.by(Direction.ASC,SortBy);
			sortChect=!sortChect;
		}
		
		Product product=new Product();
		model.addAttribute("product", product);
		List<Product> sortedProducts = productDao.findAll(sort);
	    
	    model.addAttribute("products", sortedProducts);
		return "productDetail";
	}



}
