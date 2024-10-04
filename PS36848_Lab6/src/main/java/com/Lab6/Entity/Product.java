package com.Lab6.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Products")
public class Product {
	
	@Id
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="price")
	private Double price;
	@Column(name="date")
	private Date date;
}
