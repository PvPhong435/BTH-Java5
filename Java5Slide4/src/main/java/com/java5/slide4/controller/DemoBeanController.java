package com.java5.slide4.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java5.slide4.model.Room;

@Controller
public class DemoBeanController {
	@Autowired
	@Qualifier("room1")
	Room myRoom;
	@ResponseBody
	@GetMapping("/showRoomInformation")
	public Room showRoomInformation() {
		return myRoom;
	}
}
