package com.lab5.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import lombok.val;

@Controller
public class ProductControllerLab6 {
	
	@Autowired
	ProductDAO productDao;
	@Autowired
	ProductImageDao productImageDao;
	@Autowired
	CategoryDAO categoryDao;
	@Autowired
	private ServletContext servletContext;
	private int FIRST_PAGE_NUMBER = 0;
	private int NUMBER_OF_ITEM_PER_PAGE = 4;
	
	Boolean sortChect=false;

	@GetMapping("/Lab6")
	public String getProductList(Model model)
	{
		model.addAttribute("products", productDao.findAll());
		return "productLab6";
	}
	
	@RequestMapping("/Lab6Page")
	public String getProductListPage(Model model, @RequestParam("page") Optional<Integer> page)
	{
		Pageable pageable = PageRequest.of(page.orElse(FIRST_PAGE_NUMBER), NUMBER_OF_ITEM_PER_PAGE);
		Page<Product> pages = productDao.findAll(pageable);
		List<Product> productList = pages.getContent();
		model.addAttribute("products", productList);
		model.addAttribute("page", pages);
		return "product2";
	}
	
	@RequestMapping("/Lab6Add")
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
	
	@RequestMapping("/Lab6AddNew")
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
	
	@RequestMapping("/Lab6Detail/{id}")
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
	
	@RequestMapping("/Lab6Save")
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
	
	
	
	@GetMapping("/Lab6Remove/{id}")
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
	
	@RequestMapping("/Lab6SaveAdd")
	public String saveProductAdd(Model model, @Valid @ModelAttribute("product") Product product,
	                             @RequestParam("productImages") MultipartFile[] productImages, BindingResult result) {
	    if (result.hasErrors()) {
	        return "forward:/productAdd";
	    } else {
	        // Lưu sản phẩm vào cơ sở dữ liệu trước
	        //productDao.save(product);

	        // Kiểm tra nếu sản phẩm đã được lưu thành công
	        if (productDao.findById(product.getId()) != null) {
	            List<ProductImage> imageList = new ArrayList<>();
	            String uploadDir = servletContext.getRealPath("/Image/");
	            File uploadDirectory = new File(uploadDir);

	            // Tạo thư mục nếu chưa tồn tại
	            if (!uploadDirectory.exists()) {
	                uploadDirectory.mkdirs();
	            }

	            // Duyệt qua từng file ảnh trong danh sách
	            for (MultipartFile img : productImages) {
	                if (!img.isEmpty()) {
	                    try {
	                        // Tạo tên file duy nhất
	                        String fileName = img.getOriginalFilename();
	                        System.out.println(fileName);
	                        String filePath = Paths.get(uploadDir, fileName).toString();

	                        // Lưu tệp vào thư mục
	                        img.transferTo(new File(filePath));

	                        // Tạo đối tượng ProductImage
	                        ProductImage productImage = new ProductImage();
	                        productImage.setProduct(product);
	                        productImage.setImageLink(fileName); // Đường dẫn tương đối
	                        imageList.add(productImage);
	                    } catch (Exception e) {
	                        e.printStackTrace(); // Ghi log lỗi để theo dõi
	                        return "product";
	                    }
	                }
	            }

	            // Lưu tất cả ProductImage vào cơ sở dữ liệu
	            productImageDao.saveAll(imageList);

	            // Gán danh sách hình ảnh cho sản phẩm và lưu lại
	            product.setProductImages(imageList);
	            productDao.save(product);

	            return "redirect:/productDetail/" + product.getId();
	        } else {
	            return "product";
	        }
	    }
	}

	
//	public List<ProductImage> convertMultipartFilesToProductImages(MultipartFile[] files) {
//	    List<ProductImage> productImages = new ArrayList<>();
//	    for (MultipartFile file : files) {
//	        ProductImage productImage = new ProductImage();
//	        // Giả sử ProductImage có một thuộc tính imageData để lưu trữ dữ liệu hình ảnh
//	        productImage.setImageLink(file.getOriginalFilename());
//	        productImages.add(productImage);
//	    }
//	    return productImages;
//	}
	
	@RequestMapping("/Lab6Sort/{SortBy}")
	public String SortProduct(@PathVariable("SortBy") String SortBy,Model model)
	{
		
		Sort sort= Sort.by(Direction.DESC,SortBy);
		System.out.println(SortBy);
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
	
	@RequestMapping("/Lab6Search")
	public String seartByName(Model model,
			@RequestParam("productName") String productName,
			@RequestParam("priceFrom") Double priceFrom,
			@RequestParam("priceTo") Double priceTo,
			@RequestParam("sortOrder") String sortBy)
	{
		List<Product> products;
		
		if(sortBy.equalsIgnoreCase("non"))
		{
			products=productDao.findByNameContainingAndPriceBetween(productName, priceFrom, priceTo);
		}
		else
		{
			Sort sort;
			if(sortBy.equalsIgnoreCase("asc"))
			{
				sort=Sort.by(Direction.ASC,"price");
			}
			else
			{
				sort=Sort.by(Direction.DESC,"price");
			}
			products=productDao.findByNameContainingAndPriceBetween(productName, priceFrom, priceTo,sort);
		}
		
		model.addAttribute("products", products);
		
		return "productLab6";
	}

}
