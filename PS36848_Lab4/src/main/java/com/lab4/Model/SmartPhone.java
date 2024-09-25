package com.lab4.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartPhone {
	int id;
	String name;
	Double price;
	String ram;
	String rom;
	String image[];
	String operatingSystem;
	String descripe;
	int qty=1;
}
