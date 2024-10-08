package com.lab7.dao;


import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lab7.entity.Product;
import com.lab7.entity.Report;

public interface ProductDAO extends JpaRepository<Product, Integer>{

//	@Query("SELECT o FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
//	List<Product> findByPrice(double minPrice, double maxPrice);
	//List<Product> findByPriceBetween(double minPrice, double maxPrice);

//@Query("SELECT o FROM Product o WHERE o.name LIKE ?1")
//Page<Product> findByKeywords(String keywords, Pageable pageable);

//Page<Product> findAllByNameLike(String keywords, Pageable pageable);
//
//	@Query("SELECT new Report(o.category, sum(o.price), count(o)) "
//			+ " FROM Product o "
//			+ " GROUP BY o.category"
//			+ " ORDER BY sum(o.price) DESC")
//	List<Report> getInventoryByCategory();
	
	List<Product> findByNameContainingAndPriceBetween(String name,Double priceFrom,Double priceTo);
	
	List<Product> findByNameContainingAndPriceBetween(String name,Double priceFrom,Double priceTo,Sort sort);
	
	//List<Product> findByNameContainingAndPriceBetween(String name,Double priceFrom,Double priceTo,Pageable page);
	
	Page<Product> findByNameContainingAndPriceBetween(String name,Double priceFrom,Double priceTo,Pageable page);
	
	@Query("SELECT new Report(o.category, sum(o.price), count(o)) "
			+ " FROM Product o "
			+ " GROUP BY o.category"
			+ " ORDER BY sum(o.price) DESC")
	List<Report> getInventoryByCategory();
	
}
