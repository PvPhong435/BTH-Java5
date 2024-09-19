package com.lab3.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student2 {
	private String name;
	private String email;
	private Double marks;
	private String gender;
	private String faculty;
	List<String> hobbies;
	private String image;
}
