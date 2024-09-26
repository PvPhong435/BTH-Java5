package com.lab4.Model;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	
	@NotBlank(message = "Tên Khách Hàng Không Được Để trống")
	private String name;
	
	@NotBlank(message = "Email Khách Hàng Không Được Để trống")
	@Email(message = "Định dạng email không đúng")
	private String email;
	
	 @NotBlank(message = "Số Điện thoại Khách Hàng Không Được Để trống")
	private String phone;
	 
	 @NotBlank(message = "Địa Chỉ Khách Hàng Không Được Để trống")
	private String address;
	 
	 
	private List<SmartPhone> listPhone;
	
}
