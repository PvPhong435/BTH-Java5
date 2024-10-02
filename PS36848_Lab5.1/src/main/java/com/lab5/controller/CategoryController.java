package com.lab5.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lab5.dao.CategoryDAO;
import com.lab5.entity.Category;


@Controller
public class CategoryController {
	@Autowired
	CategoryDAO categoryDao;

	@RequestMapping("/category/index")
	public String index(Model model) {
		List<Category> categories = categoryDao.findAll();
		
		long countCategories = categoryDao.count();
		Category item = new Category();
		model.addAttribute("item", item);
		model.addAttribute("numCategories", countCategories);
		List<Category> items = categoryDao.findAll();
		model.addAttribute("items", items);
		model.addAttribute("categories", categories);
		return "category/index";
	}

	@RequestMapping("/category/edit/{id}")
	public String edit(Model model, @PathVariable("id") String id) {
		//Category item = categoryDao.findById(id).get();
		//model.addAttribute("item", item);
		List<Category> items = categoryDao.findAll();
		model.addAttribute("items", items);
		return "category/index";
	}

	@RequestMapping("/category/create")
	public String create(Category category, Model model) {
		if(!categoryDao.existsById(category.getId()))
			categoryDao.save(category);
		else
			model.addAttribute("errorMessage", "Category: " +category.getId() + " is existed!" );
		return "forward:/category/index";
	}

	@RequestMapping("/category/update")
	public String update(Category item) {
		categoryDao.save(item);
		return "redirect:/category/edit/" + item.getId();
	}

	@RequestMapping("/category/delete/{id}")
	public String create(@PathVariable("id") String id) {
		//categoryDao.deleteById(id);
	
		return "redirect:/category/index";
	}
}