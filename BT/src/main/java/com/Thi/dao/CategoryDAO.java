package com.Thi.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Thi.entity.Category;


public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
