package com.Thi.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.Thi.entity.OrderDetail;




public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long>{
}