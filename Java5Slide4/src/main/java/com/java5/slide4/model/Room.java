package com.java5.slide4.model;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@Component
public class Room {
	private String id = "T1006";
	private String name ="T1006";
	private int numberOfSeats = 40;
	
}
