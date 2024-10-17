package com.assignment.caulong.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.caulong.models.Image;

public interface ImageDAO extends JpaRepository<Image, String> {

}
