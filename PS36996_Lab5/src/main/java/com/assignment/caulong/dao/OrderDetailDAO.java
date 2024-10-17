package com.assignment.caulong.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.caulong.models.OrderDetail;

public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
}