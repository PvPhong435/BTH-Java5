package com.Thi.controller;

import java.io.File;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Thi.dao.AccountDAO;
import com.Thi.dao.CategoryDAO;
import com.Thi.dao.ProductDAO;
import com.Thi.dao.ProductImageDao;
import com.Thi.entity.Account;
import com.Thi.entity.Category;
import com.Thi.entity.Product;
import com.Thi.entity.ProductImage;
import com.Thi.entity.ProductImageId;
import com.Thi.entity.Report;
import com.Thi.entity.Session;
import com.Thi.service.AccountService;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import lombok.val;

@Controller
public class ProductControllerLab6 {
	Logger logger = LogManager.getLogger(ProductControllerLab6.class);
	@Autowired
	ProductDAO productDao;
	@Autowired
	ProductImageDao productImageDao;
	@Autowired
	CategoryDAO categoryDao;
	@Autowired
	AccountService accService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	AccountDAO accountDao;
	@Autowired
	com.Thi.service.SessionService session;
	private int FIRST_PAGE_NUMBER = 0;
	private int NUMBER_OF_ITEM_PER_PAGE = 4;

	List<Product> products;

	private Boolean checkLogin = false;
	private Boolean checkSort = false;
	private Account accPresent=new Account();

	Boolean sortChect = false;

	@GetMapping("/Lab6")
	public String getProductListPage(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("productName") Optional<String> productName,
			@RequestParam("priceFrom") Optional<Double> priceFrom, @RequestParam("priceTo") Optional<Double> priceTo,
			@RequestParam("sortOrder") Optional<String> sortBy) {
		
		if (checkLogin) {
			model.addAttribute("loginButton", "Đăng Xuất");
			accPresent=(Account)session.get("user");
			model.addAttribute("username", accPresent.getUsername());
			model.addAttribute("role", !accPresent.isAdmin()?"Nhân Viên":"Admin");
			
		} else {
			model.addAttribute("loginButton", "Đăng Nhập");
				accPresent=null;
				model.addAttribute("username", "NULL");
				model.addAttribute("role", "NULL");
		}
		if (!checkSort) {
			Pageable pageable = PageRequest.of(page.orElse(FIRST_PAGE_NUMBER), NUMBER_OF_ITEM_PER_PAGE);
			Page<Product> pages = productDao.findAll(pageable);
			List<Product> productList = pages.getContent();
			model.addAttribute("products", productList);
			model.addAttribute("page", pages);
			
		} else {
			Pageable pageable;
			Page<Product> pages;
			System.out.println("đang chạy ở trường hợp else");
			if (sortBy.equals("non")) {
				pageable = PageRequest.of(page.orElse(FIRST_PAGE_NUMBER), NUMBER_OF_ITEM_PER_PAGE);
				 pages = productDao.findByNameContainingAndPriceBetween(productName.orElse(""),
						priceFrom.orElse(Double.MIN_VALUE), priceTo.orElse(Double.MAX_VALUE), pageable);
			} else {
				Sort sort;
				if (sortBy.equals("asc")) {
					// sort=Sort.by(Direction.ASC,"price");
					pageable = PageRequest.of(page.orElse(FIRST_PAGE_NUMBER), NUMBER_OF_ITEM_PER_PAGE,
							Sort.by("price").ascending());
				} else {
					// sort=Sort.by(Direction.DESC,"price");
					pageable = PageRequest.of(page.orElse(FIRST_PAGE_NUMBER), NUMBER_OF_ITEM_PER_PAGE,
							Sort.by("price").descending());
				}
				 pages = productDao.findByNameContainingAndPriceBetween(productName.orElse(""),
						priceFrom.orElse(Double.MIN_VALUE), priceTo.orElse(Double.MAX_VALUE), pageable);
				
			}
			products=pages.getContent();
			
			model.addAttribute("productName", productName.orElse(""));
			model.addAttribute("priceFrom", priceFrom.orElse(null));
			model.addAttribute("priceTo", priceTo.orElse(null));
			model.addAttribute("sortOrder", sortBy.orElse("non"));
			model.addAttribute("products", products);
			model.addAttribute("page", pages);
		}

		return "productLab6";
	}

	
	@GetMapping("/Lab6Reload")
	public String reload()
	{
		checkSort=false;
		return "redirect:/Lab6";
	}
	@GetMapping("/")
	public String getLogin() {
		return "redirect:/Lab6";
	}

	@RequestMapping("/Lab6Add")
	public String GetProductAdd(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("products", productDao.findAll());

		List<Category> categories = categoryDao.findAll();
		System.out.println("all category");
		System.out.println(categories);
		model.addAttribute("categories", categoryDao.findAll());
		return "productDetail";
	}

	@RequestMapping("/Lab6AddNew")
	public String GetProductAddNew(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);

		List<Category> categories = categoryDao.findAll();
		System.out.println("all category");
		System.out.println(categories);
		model.addAttribute("categories", categoryDao.findAll());
		return "productAdd";
	}

	@RequestMapping("/Lab6Detail/{id}")
	public String GetProductDetail(@PathVariable("id") int id, Model model) {
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
	public String saveResult(Model model, @Valid @ModelAttribute("product") Product product, BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Lỗi khi sửa dữ liệu");
			return "productDetail/" + product.getId();
		} else {
			productDao.save(product);
			return "redirect:/productDetail/" + product.getId();
		}
//		Optional<Product> optionalProduct = productDao.findById(product.getId());
//		Product product2 = optionalProduct.orElse(null); // Lấy sản phẩm hoặc null nếu không tồn tại
//		model.addAttribute("product", product2);
//		model.addAttribute("products", productDao.findAll());

	}

	@GetMapping("/Lab6Remove/{id}")
	public String removeProduct(@PathVariable("id") int id, Model model) {
		try {
			ProductImageId productImageId = new ProductImageId();
			productImageId.setProductId(id);
			productImageDao.deleteById(productImageId);
			productDao.deleteById(id);
			System.out.println("Xóa dữ liệu thành công");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Xóa dữ liệu Thất Bại");
		}

		return "redirect:/Lab6";
	}

	@RequestMapping("/Lab6SaveAdd")
	public String saveProductAdd(Model model, @Valid @ModelAttribute("product") Product product,
			@RequestParam("productImages") MultipartFile[] productImages, BindingResult result) {
		if (result.hasErrors()) {
			return "forward:/productAdd";
		} else {
			// Lưu sản phẩm vào cơ sở dữ liệu trước
			// productDao.save(product);

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

				return "redirect:/Lab6Detail/" + product.getId();
			} else {
				return "product";
			}
		}
	}

	@RequestMapping("/Lab6Sort/{SortBy}")
	public String SortProduct(@PathVariable("SortBy") String SortBy, Model model) {

		Sort sort = Sort.by(Direction.DESC, SortBy);
		System.out.println(SortBy);
		if (sortChect) {
			sort = Sort.by(Direction.DESC, SortBy);
			sortChect = !sortChect;
		} else {
			sort = Sort.by(Direction.ASC, SortBy);
			sortChect = !sortChect;
		}

		Product product = new Product();
		model.addAttribute("product", product);
		List<Product> sortedProducts = productDao.findAll(sort);

		model.addAttribute("products", sortedProducts);
		return "productDetail";
	}

	@PostMapping("/Lab6Search")
	public String seartByName(Model model, @RequestParam("productName") Optional<String> productName,
			@RequestParam("priceFrom") Optional<Double> priceFrom, @RequestParam("priceTo") Optional<Double> priceTo,
			@RequestParam("sortOrder") String sortBy, @RequestParam("page") Optional<Integer> page) {

		checkSort = true;
		Pageable pageable;
		Page<Product> pages;
//		if (sortBy.equalsIgnoreCase("non")) {
//			products = productDao.findByNameContainingAndPriceBetween(productName.orElse(""),
//					priceFrom.orElse(Double.MIN_VALUE), priceTo.orElse(Double.MAX_VALUE));
//		} else {
//			Sort sort;
//			if (sortBy.equalsIgnoreCase("asc")) {
//				sort = Sort.by(Direction.ASC, "price");
//			} else {
//				sort = Sort.by(Direction.DESC, "price");
//			}
//			products = productDao.findByNameContainingAndPriceBetween(productName.orElse(""),
//					priceFrom.orElse(Double.MIN_VALUE), priceTo.orElse(Double.MAX_VALUE), sort);
//		}
		
		if (sortBy.equals("non")) {
			pageable = PageRequest.of(page.orElse(FIRST_PAGE_NUMBER), NUMBER_OF_ITEM_PER_PAGE);
			pages = productDao.findByNameContainingAndPriceBetween(productName.orElse(""),
					priceFrom.orElse(Double.MIN_VALUE), priceTo.orElse(Double.MAX_VALUE), pageable);
		} else {
			Sort sort;
			if (sortBy.equals("asc")) {
				// sort=Sort.by(Direction.ASC,"price");
				pageable = PageRequest.of(page.orElse(FIRST_PAGE_NUMBER), NUMBER_OF_ITEM_PER_PAGE,
						Sort.by("price").ascending());
			} else {
				// sort=Sort.by(Direction.DESC,"price");
				pageable = PageRequest.of(page.orElse(FIRST_PAGE_NUMBER), NUMBER_OF_ITEM_PER_PAGE,
						Sort.by("price").descending());
			}
			pages = productDao.findByNameContainingAndPriceBetween(productName.orElse(""),
					priceFrom.orElse(Double.MIN_VALUE), priceTo.orElse(Double.MAX_VALUE), pageable);
		}
		
		
		products=pages.getContent();
		model.addAttribute("productName", productName.orElse(""));
		model.addAttribute("priceFrom", priceFrom.orElse(null));
		model.addAttribute("priceTo", priceTo.orElse(null));
		model.addAttribute("sortOrder", sortBy);
		model.addAttribute("products", products);
		model.addAttribute("page", pages);
		if(accPresent!=null)
		{
			model.addAttribute("username", accPresent.getUsername());
			model.addAttribute("role", !accPresent.isAdmin()?"Nhân Viên":"Admin");
		}
		else
		{
			model.addAttribute("username", "NULL");
			model.addAttribute("role", "NULL");
		}
		
		if (checkLogin) {
			model.addAttribute("loginButton", "Đăng Xuất");
		} else {
			model.addAttribute("loginButton", "Đăng Nhập");
		}
		

		return "productLab6";
	}

	@RequestMapping("/Lab6Report")
	public String getReport(Model model) {
		List<Report> listReport = productDao.getInventoryByCategory();
		model.addAttribute("categories", listReport);

		return "productThongKe";
	}

	@GetMapping("/Lab6Login")
	public String GetLogin(Model model, @RequestParam("error") Optional<String> error) {
		if (checkLogin) {
			Account accountLogin = (Account) session.get("user");
			if (accountLogin != null)
				session.set("user", null);
			checkLogin = false;
			accPresent=null;
			
				model.addAttribute("username", "NULL");
				model.addAttribute("role", "NULL");
			
			return "redirect:/";
		} else {
			Account account = new Account();
			model.addAttribute("account", account);
			model.addAttribute("error", error);

		}

		return "Login";
	}

	@PostMapping("/Lab6Login")
	public String Checklogin(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password,@ModelAttribute() Account account) {
		logger.info("User Login: " + username);
		try {
			Account user = accountDao.findByUsernameLike(username);
			if (user == null || !user.getPassword().equals(password)) {
				logger.warn("Login Fail: " + username);
				model.addAttribute("error", "Invalid username or password!");
			} else {
				checkLogin = true;
				session.set("user", user);
				String uri = (String) session.get("security-uri");
				logger.info("Login success: " + username);
				if (uri != null) {
					accPresent=user;
					return "redirect:" + uri;
				} else {
					model.addAttribute("message", "Login succeed");

					return "redirect:/Lab6";
				}

			}
		} catch (Exception e) {
			logger.error("Error Login: " + username);
			model.addAttribute("message", "Invalid username");
		}
		return "Login";
	}
	
	@GetMapping("/sendMail")
	public String sendMail()
	{
		if(accPresent.isAdmin())
		{
			return "sendMail";
		}
		else
		{
			return "notAdmin";
		}
	}
	
}
