package com.lab5.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.lab5.entity.Category;


public interface CategoryDAO extends JpaRepository<Category, String>{

}
