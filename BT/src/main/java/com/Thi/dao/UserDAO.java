package com.Thi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Thi.entity.User;

public interface UserDAO extends JpaRepository<User, String> {
	List<User> findByFullnameContaining(String fullName);
	List<User> findByFullnameContaining(String fullName,org.springframework.data.domain.Sort sort);
	
	//List<User> findByFullnameContainingAndPriceBetween(String fullName,Double priceFrom,Double priceto);
	
	//List<User> findByPriceBetween(Double priceFrom,Double priceto);
}
