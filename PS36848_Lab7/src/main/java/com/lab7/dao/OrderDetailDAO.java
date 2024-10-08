package com.lab7.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.lab7.entity.OrderDetail;




public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
}