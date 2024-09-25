package com.lab4.service;

import java.util.Collection;

import com.lab4.Model.SmartPhone;



public interface ShoppingCartServicePhone {
	
	SmartPhone add(Integer id);
	
	String remove(Integer id);
	
	SmartPhone update(Integer id,String qty);
	
	void clear();
	
	Collection<SmartPhone>getPhones();
	
	int getCount();
	
	Double getAmount();
	
}
