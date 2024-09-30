package com.lab5.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lab5.entity.Student;

public interface StudentDAO extends JpaRepository<Student, Integer> {

}
