package com.lab4.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	private String name;
	private String email;
	private String phone;
	private String address;
	private List<SmartPhone> listPhone;
	
}
