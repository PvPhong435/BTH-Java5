package com.java5.slide4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.java5.slide4.model.Room;

@Configuration
public class MyApplicationConfig {
	
	@Bean("room1")
	public Room getRoom() {
		return new Room("T802", "T802", 40);
	}
	
	@Bean("room2")
	public Room getRoom1() {
		Room r = new  Room();
		r.setId("F1111");
		r.setNumberOfSeats(100);
		return r;
	}
//	
//	@Bean("roomBean2")
//	public Room getRoom2() {
//		Room r = new  Room();
//		r.setId("F2222");
//		return r;
//	}
//	
//	@Primary
//	@Bean("roomBean2")
//	public Room getRoom2() {
//		Room r = new  Room();
//		r.setId("F2222");
//		return r;
//	}
}
