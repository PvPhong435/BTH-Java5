package com.lab5.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.lab5.entity.OrderDetail;




public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
}