package com.assignment.caulong.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.caulong.models.Category;

public interface CategoryDAO extends JpaRepository<Category, String>{
//	@Query("Select new CategoryIncome(category.name,sum(OrderDetail.quantity),sum(OrderDetail.price*OrderDetail.quantity")
//	List<CategoryIncome> 
}
