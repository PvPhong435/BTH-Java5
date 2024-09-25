package com.lab4.util;

import java.util.HashMap;
import java.util.Map;

import com.lab4.Model.SmartPhone;



public class Phone {
	public static Map<Integer, SmartPhone> phone = new HashMap<>();
	static {
		phone.put(1, new SmartPhone(1,"Iphone 11",13000.0,"8gb","128gb",new String[]{"iphone11_1.pnj","iphone11_2.pnj","iphone11_3.pnj"},"IOS","the iphone 11 ",1));
		phone.put(1, new SmartPhone(1,"Iphone 12",14000.0,"8gb","128gb",new String[]{"iphone12_1.pnj","iphone12_2.pnj","iphone12_3.pnj"},"IOS","the iphone 12 ",1));
		phone.put(1, new SmartPhone(1,"Iphone 13",15000.0,"8gb","128gb",new String[]{"iphone13_1.pnj","iphone13_2.pnj","iphone13_3.pnj"},"IOS","the iphone 13 ",1));
		phone.put(1, new SmartPhone(1,"Iphone 14",16000.0,"8gb","128gb",new String[]{"iphone14_1.pnj","iphone14_2.pnj","iphone14_3.pnj"},"IOS","the iphone 14 ",1));
	}
}
