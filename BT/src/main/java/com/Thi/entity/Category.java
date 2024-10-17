package com.Thi.entity;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Categories")
public class Category implements Serializable{
	@Id
	Integer id;
	@Column(name = "Name")
	String name;
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	List<Product> products;
	
	@Override
	public String toString() {
	    return "Category{" +
	            "id=" + id +
	            ", name='" + name + '\'' +
	            // Không gọi toString() của danh sách products để tránh đệ quy
	            '}';
	}

}
