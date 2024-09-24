package com.lab4.service;

import java.util.Collection;

import com.lab4.Model.Item;

public interface ShoppingCartService {
	
	Item add(Integer id);
	
	String remove(Integer id);
	
	Item update(Integer id,String qty);
	
	void clear();
	
	Collection<Item>getItems();
	
	int getCount();
	
	Double getAmount();
	
}
