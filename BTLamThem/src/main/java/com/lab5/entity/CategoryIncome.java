package com.lab5.entity;

import java.io.Serializable;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryIncome implements Serializable {
	@Id
	private String categoryName;
	private Double totalMoney;
	private Long totalQuantity;
	
	
}
