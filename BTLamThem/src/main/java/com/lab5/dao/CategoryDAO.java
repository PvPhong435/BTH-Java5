package com.lab5.dao;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lab5.entity.Category;
import com.lab5.entity.CategoryIncome;

public interface CategoryDAO extends JpaRepository<Category, Integer>{
	
	@Query("SELECT new com.lab5.entity.CategoryIncome(category.name, SUM(orderDetail.price * orderDetail.quantity), SUM(orderDetail.quantity)) "
	         + "FROM Category category, Product product, OrderDetail orderDetail, Order order "
	         + "WHERE category.id = product.category.id "
	         + "AND product.id = orderDetail.product.id "
	         + "AND orderDetail.order.id = order.id "
	         + "AND order.createDate BETWEEN :fromDate AND :toDate "
	         + "GROUP BY category.name "
	         + "ORDER BY SUM(orderDetail.price * orderDetail.quantity) DESC")
	    List<CategoryIncome> getCategoryIncome(Date fromDate, Date toDate);
}
