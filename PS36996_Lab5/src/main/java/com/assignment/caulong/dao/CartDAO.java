package com.assignment.caulong.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.caulong.models.Account;
import com.assignment.caulong.models.Cart;
import com.assignment.caulong.models.CartId;

public interface CartDAO extends JpaRepository<Cart, CartId> {
	List<Cart> findByAccount(Account account);
}
