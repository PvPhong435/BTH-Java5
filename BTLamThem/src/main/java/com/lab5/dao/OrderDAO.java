package com.lab5.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.lab5.entity.Order;



public interface OrderDAO extends JpaRepository<Order, Long>{
}
