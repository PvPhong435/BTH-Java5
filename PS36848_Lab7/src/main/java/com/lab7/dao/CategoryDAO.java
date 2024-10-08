package com.lab7.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lab7.entity.Category;


public interface CategoryDAO extends JpaRepository<Category, Integer>{

}
