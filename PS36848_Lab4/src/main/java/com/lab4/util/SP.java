package com.lab4.util;

import java.util.HashMap;

import java.util.Map;

import com.lab4.Model.Item;


public class SP {
	public static Map<Integer, Item> item = new HashMap<>(); 
	static {
		item.put(1, new Item(1,"Iphone 13" , 23.0, 0,"iPhone13.jpg"));
		item.put(2, new Item(2,"Iphone 14" , 12.0, 0,"iphone14.jpg"));
		item.put(3, new Item(3,"Iphone 15" , 33.0, 0,"iphone-15.png"));
		item.put(4, new Item(4,"Realme 9" , 42.0, 0,"realme9.png"));
	}
}
