package com.Thi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.Thi.dao.AccountDAO;
import com.Thi.dao.CategoryDAO;
import com.Thi.dao.ProductDAO;
import com.Thi.dao.ProductImageDao;
import com.Thi.dao.UserDAO;
import com.Thi.entity.Account;
import com.Thi.entity.User;
import com.Thi.service.AccountService;

import jakarta.servlet.ServletContext;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@Controller
public class UserController {

	Logger logger = LogManager.getLogger(ProductControllerLab6.class);
	@Autowired
	AccountService accService;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	AccountDAO accountDao;
	@Autowired
	UserDAO userDao;
	@Autowired
	com.Thi.service.SessionService session;

	private Boolean checkLogin = false;
	private Boolean checkSort = false;
	private Boolean checkSelect = false;
	private String messager = null;
	private String errMess = null;
	private Account accPresent = null;

	Boolean sortChect = false;

	@GetMapping("/Thi")
	public String getBaiThi(Model model) {
		User user = new User();
		checkSelect = false;
		List<User> listUser = userDao.findAll();
		model.addAttribute("user", user);
		model.addAttribute("userList", listUser);
		model.addAttribute("checkSelected", checkSelect);
		model.addAttribute("errMess", errMess);
		model.addAttribute("Mess", messager);
		if(checkLogin)
		{
			accPresent=new Account();
			accPresent=(Account) session.get("user");
			model.addAttribute("account", accPresent);
		}
		else
		{
			model.addAttribute("account", null);
		}

		return "USER";
	}

	@GetMapping("/Detail/{id}")
	public String getUser(@PathVariable() String id, Model model) {
		User user = userDao.findById(id).orElse(null);
		List<User> listUser = userDao.findAll();
		model.addAttribute("user", user);
		checkSelect = true;
		System.out.println(checkSelect);
		model.addAttribute("userList", listUser);
		model.addAttribute("checkSelected", checkSelect);
		model.addAttribute("Mess", messager);
		model.addAttribute("errMess", errMess);
		return "USER";
	}

	@GetMapping("/Refesh")
	public String GetRefesh() {
		messager = null;
		errMess = null;
		return "redirect:/Thi";
	}

	@RequestMapping("/AddOrUpdate")
	public String AddNewUser(@Valid @ModelAttribute("user") User user,BindingResult result, Model model) {
		if (result.hasErrors()) 
		{
			model.addAttribute("message", "Some fields are not valid. Please fix!");
			System.out.println("Some fields are not valid. Please fix!"+result.toString());
			 model.addAttribute("user", user);
			return "USER";
		} 
		else 
		{
			try {
				if (!checkSelect) {
					if (!userDao.findById(user.getId()).isEmpty()) {
						messager=null;
						errMess = "User ID : " + user.getId() + " đã tồn tại trong cơ sở dữ liệu";
						return "redirect:/Thi";
					} else {
						errMess=null;
						userDao.save(user);
						messager = "Thêm dữ liệu thành công";
						model.addAttribute("Mess", messager);
						
						return "redirect:/Thi";
					}
				} else {
					userDao.save(user);
					messager = "Sửa dữ liệu thành công";
					model.addAttribute("Mess", messager);
				
					return "redirect:/Detail/" + user.getId();
				}
			} catch (Exception e) {
				if (!checkSelect) {
					errMess = "Lỗi trong quá trình thêm dữ liệu\n" + e.toString();
				} else {
					errMess = "Lỗi trong quá trình Sửa dữ liệu\n" + e.toString();
				}
				model.addAttribute("errMess", errMess);
				return "redirect:/Thi";
			}
		}
		
	}

	@GetMapping("/Remove/{id}")
	public String remove(@PathVariable() String id, Model model) {
		User user = userDao.findById(id).orElse(null);
		userDao.delete(user);
		messager="Xóa dữ liệu thành công";
		System.out.println("Xóa thành công");
		return "redirect:/Thi";
	}

	@GetMapping("/Login")
	public String GetLogin(Model model, @RequestParam("error") Optional<String> error) {

		Account account = new Account();
		accPresent = new Account();
		model.addAttribute("account", account);
		model.addAttribute("error", error);
		return "Login";
	}

	@GetMapping("/LogOut")
	public String GetLogOut(Model model) {
		Account accountLogin = (Account) session.get("user");
		if (accountLogin != null) {
			session.set("user", null);
			checkLogin = false;
			accPresent = null;
		}
		return "redirect:/Thi";
	}

	@PostMapping("/Login")
	public String Checklogin(Model model, @RequestParam("username") String username,
			@RequestParam("password") String password, @ModelAttribute() Account account) {
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
					accPresent = user;
					return "redirect:" + uri;
				} else {
					model.addAttribute("message", "Login succeed");

					return "redirect:/Thi";
				}
			}
		} catch (Exception e) {
			logger.error("Error Login: " + username);
			model.addAttribute("message", "Invalid username");
		}
		return "Login";
	}

	@GetMapping("/Search")
	public String getSearch(Model model,@RequestParam("fullname") Optional<String> fullname) {
		List<User> listUsers=userDao.findByFullnameContaining(fullname.orElse(""));
		System.out.println(fullname);
		User user;
		if(listUsers.size()==1)
		{
			user=listUsers.get(0);
		}
		else
		{
			user= new User();
		}
		 
		checkSelect = false;
		model.addAttribute("user", user);
		model.addAttribute("userList", listUsers);
		model.addAttribute("checkSelected", checkSelect);
		model.addAttribute("errMess", errMess);
		model.addAttribute("Mess", messager);
		model.addAttribute("fullname", fullname.orElse(null));
		if(checkLogin)
		{
			accPresent=new Account();
			accPresent=(Account) session.get("user");
			model.addAttribute("account", accPresent);
		}
		else
		{
			model.addAttribute("account", null);
		}
		return "USER";
	}
	
	//Nháp 
	@RequestMapping("/Search2")
	public String seartByName(Model model,
			@RequestParam("productName") Optional<String> productName,
			@RequestParam("priceFrom") Optional<Double> priceFrom,
			@RequestParam("priceTo") Optional<Double> priceTo,
			@RequestParam("sortOrder") String sortBy)
	{
		List<User> users;
		
//		if(sortBy.equalsIgnoreCase("non"))
//		{
//			users=userDao.findByNameContainingAndPriceBetween(productName.orElse(""), priceFrom.orElse(Double.MIN_VALUE), priceTo.orElse(Double.MAX_VALUE));
//		}
//		else
//		{
//			Sort sort;
//			if(sortBy.equalsIgnoreCase("asc"))
//			{
//				sort=Sort.by(Direction.ASC,"price");
//			}
//			else
//			{
//				sort=Sort.by(Direction.DESC,"price");
//			}
//			products=productDao.findByNameContainingAndPriceBetween(productName.orElse(""), priceFrom.orElse(Double.MIN_VALUE), priceTo.orElse(Double.MAX_VALUE),sort);
//		}
		
		model.addAttribute("productName", productName.orElse(""));
		model.addAttribute("priceFrom", priceFrom.orElse(null));
		model.addAttribute("priceTo", priceTo.orElse(null));
		model.addAttribute("sortOrder", sortBy);
		//model.addAttribute("userList", users);
		
		return "productLab6";
	}
}
