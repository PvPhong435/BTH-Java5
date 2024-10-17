package com.assignment.caulong.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assignment.caulong.models.Category;
import com.assignment.caulong.models.Product;
import com.assignment.caulong.models.Report;

public interface ProductDAO extends JpaRepository<Product, Integer>{

//	@Query("SELECT o FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
//	List<Product> findByPrice(double minPrice, double maxPrice);
	List<Product> findByPriceBetween(double minPrice, double maxPrice);

//@Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
//Page<Product> findByKeywords(String keywords, Pageable pageable);

Page<Product> findAllByIgnoreCaseNameContaining(String keywords, Pageable pageable);
Page<Product> findAllByCategory(Category category, Pageable pageable);

}
