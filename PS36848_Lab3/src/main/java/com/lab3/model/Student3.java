package com.lab3.model;

import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student3 {
	@NotBlank(message = "Name must not be blank")
	private String name;
	
	 @NotBlank(message = "Email must not be blank")
	@Email(message = "Định dạng email không đúng")
	private String email;
	
	@NotNull(message = "Marks must not be null")
	@DecimalMin(message = "Lương phải lớn hơn 1",value = "1")
	private Double marks;
	
	@NotBlank(message = "Gender must not be blank")
	private String gender;
	
	@NotBlank(message = "Faculty must not be blank")
	private String faculty;
	
	 @NotEmpty(message = "Hobbies must not be empty")
	List<String> hobbies;
	
	private String image;
}
