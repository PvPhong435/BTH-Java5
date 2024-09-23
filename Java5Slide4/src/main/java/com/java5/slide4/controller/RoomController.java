package com.java5.slide4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java5.slide4.model.Room;
import com.java5.slide4.service.RoomService;
import java.util.List;
import java.util.Random;

@Controller
public class RoomController {
//	@Autowired
//	@Qualifier("room1")
//	Room myRoom;
//	@ResponseBody
//	@GetMapping("/getRoom")
//	public Room getRoomInformation() {
//		return myRoom;
//	}
	
	public  String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    } 
	@Autowired
	RoomService roomService;
	
	@ResponseBody
	@GetMapping("/addRoom")
	public List<Room> addRoom(){
		roomService.addRoom(new Room(this.generateRandomString(4),this.generateRandomString(8), 50));
		return roomService.listRooms;
	}
	
	@ResponseBody
	@GetMapping("/deleteRoom/{id}")
	public List<Room> deleteRoom(@PathVariable("id") String id){
		roomService.deleteRoom(id);
		return roomService.listRooms;
	}
	
//	@GetMapping("/addRoom")
//	@ResponseBody
//	public List<Room> adddRoom()
//	{
//		Room room = new Room("T55","T55", 30);
//		
//	}
//	
//	@Autowired
//	@Qualifier("roomBean1")
//	Room myRoom;
//	
//	@ResponseBody
//	@GetMapping("/room/getRoom")
//	public Room getRoom() {
//		return myRoom;
//	}	
//	
//	@Autowired
//	@Qualifier("roomBean2")
//	Room room2;
//	
//	@ResponseBody
//	@GetMapping("/room/getRoom2")
//	public Room getRoom2() {
//		return room2;
//	}
//	@Autowired
//	@Qualifier("roomBean2")
//	Room myRoom;
//	
//	@ResponseBody
//	@GetMapping("/room/getRoom")
//	public Room getRoom() {
//		return myRoom;
//	}
}
