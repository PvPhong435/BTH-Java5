package com.Thi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name = "[User]")
public class User {
	@Id
	@Column(name="Id")
	@NotBlank(message = "User Id Không Được Để trống")
	private String id;
	
	@Column(name="Password")
	@NotBlank(message = "Password Không Được Để trống")
	private String password;
	
	@Column(name="Fullname")
	@NotBlank(message = "fullname Không Được Để trống")
	private String fullname;
	
	@Column(name="Email")
	@NotBlank(message = "email Không Được Để trống")
	@Email(message = "Định dạng email không đúng")
	private String email;
	
	@Column(name="Admin")
	private Boolean admin;
	
}
