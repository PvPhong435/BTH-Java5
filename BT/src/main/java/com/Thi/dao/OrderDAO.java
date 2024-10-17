package com.Thi.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.Thi.entity.Order;



public interface OrderDAO extends JpaRepository<Order, Long>{
}
