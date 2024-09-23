package com.java5.slide4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	Integer id;
	String name;
	
	double price;
	int qty = 1;//Số lượng sản phẩm
	String img;//URL link hình minh họa sản phẩm
}
