package com.lab3.model;

import java.util.List;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student4 {
	 @NotBlank(message = "{NotEmpty.sv4.name}")
	private String name;
	
	@NotBlank(message = "{NotEmpty.sv4.email}")
	@Email(message = "{Email.sv4,email}")
	private String email;
	
	@NotNull(message = "{NotNull.sv4.marks}")
	@DecimalMin(message = "Lương phải lớn hơn 1",value = "1")
	private Double marks;
	
	@NotBlank(message = "{NotEmpty.sv4.gender}")
	private String gender;
	
	@NotBlank(message = "{dattentuyynhungphaidehieu}")
	private String faculty;
	
	 @NotEmpty(message = "{hobbies}")
	List<String> hobbies;
	
	private String image;
}
