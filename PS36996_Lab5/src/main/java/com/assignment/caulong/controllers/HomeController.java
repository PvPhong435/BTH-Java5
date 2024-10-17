package com.assignment.caulong.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assignment.caulong.dao.AccountDAO;
import com.assignment.caulong.dao.CartDAO;
import com.assignment.caulong.dao.ProductDAO;
import com.assignment.caulong.models.Account;
import com.assignment.caulong.models.Cart;
import com.assignment.caulong.models.CartId;
import com.assignment.caulong.models.Product;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	private AccountDAO accDAO;
	private CartDAO cartDAO;
	private ProductDAO productDAO;

	public HomeController(AccountDAO accDAO, CartDAO cartDAO, ProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
		this.accDAO = accDAO;
		this.cartDAO = cartDAO;
	}

	@GetMapping("/")
	public String getHomePage() {
		return "index";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@PostMapping("/login")
	public String postLogin(@RequestParam String username, @RequestParam String password, HttpSession session) {
		Account account = accDAO.findByUsername(username);
		if (account == null) {
			session.setAttribute("errorMessage", "Wrong account or password");
			return "login";
		}
		if (account.getPassword().equals(password)) {
			System.out.println(session.getId());
			session.setAttribute("user", account);
			String secureURI = (String) session.getAttribute("secureURI");

			if (secureURI != null) {
				return "redirect:" + secureURI;
			}
			return "redirect:/";
		}
		session.setAttribute("errorMessage", "Wrong account or password");
		return "login";
	}

	@GetMapping("/user/cart")
	public String getUserCart(Model model, HttpSession session) {
		Account account = (Account) session.getAttribute("user");
		List<Cart> userCart = cartDAO.findByAccount(account);
		model.addAttribute("cartItems", userCart);
		return "orderForm";
	}

	@GetMapping("/addcart/{id}")
	public String addToCart(@PathVariable int id, HttpSession session) {
		System.out.println(session.getId());
		Account account = (Account) session.getAttribute("user");
		List<Cart> userCart = cartDAO.findByAccount(account);
		for(Cart cart:userCart) {
			if(cart.getProduct().getId()==id) {
				System.out.println("Yes");
				cart.setQuantity(cart.getQuantity()+1);
				cartDAO.save(cart);
				return "redirect:/user/cart";
			}
		}
		Product product = productDAO.findById(id).orElse(null);
		Cart cart = new Cart();
		CartId cartid=new CartId();
		cart.setCartId(cartid);
		cart.setAccount(account);
		cart.setProduct(product);
		cart.setQuantity(1);
		cartDAO.save(cart);
		return "redirect:/user/cart";
	}

	@GetMapping("/register")
	public String getRegister() {
		return "register";
	}
	@GetMapping("/report")
	public String getReport() {
		return "report";
	}
	@GetMapping("/error1")
	public String getError() {
		return "Error1";
	}
}
