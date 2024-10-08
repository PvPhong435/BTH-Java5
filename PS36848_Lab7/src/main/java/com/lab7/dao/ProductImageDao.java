package com.lab7.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lab7.entity.ProductImage;
import com.lab7.entity.ProductImageId;

import jakarta.transaction.Transactional;

public interface ProductImageDao extends JpaRepository<ProductImage, ProductImageId> {

//	@Transactional
//    @Modifying
//    @Query("DELETE FROM product_image pi WHERE pi.product_id = ?1")
//    void deleteByProductId(Integer productId);
}
