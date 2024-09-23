package com.java5.slide4.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java5.slide4.model.Room;

@Service
public class RoomService {
	public static List<Room> listRooms = new ArrayList<>();
	
	public Room addRoom(Room newRoom) {
		if(isExist(newRoom.getId()))
			return null;
		else {
			this.listRooms.add(newRoom);
			return newRoom;
		}
	}
	
	public boolean deleteRoom(String id) {
		if(!isExist(id))
			return false;
		else {
			for(int i = 0; i<listRooms.size(); i++) {
				if(listRooms.get(i).getId().equalsIgnoreCase(id)) {
					listRooms.remove(i);
					break;
				}
			}
			return true;
		}
	}
	public boolean isExist(String id) {
		for(Room room:listRooms)
			if(room.getId().equalsIgnoreCase(id))
				return true;
		return false;
			
	}
	
}
