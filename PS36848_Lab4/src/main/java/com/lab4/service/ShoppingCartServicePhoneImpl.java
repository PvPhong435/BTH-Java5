package com.lab4.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.lab4.Model.SmartPhone;
import com.lab4.util.Phone;

@SessionScope
@Service
public class ShoppingCartServicePhoneImpl implements ShoppingCartServicePhone {

	Map<Integer, SmartPhone> map = new HashMap<>();
	
	@Override
	public SmartPhone add(Integer id) {
		SmartPhone SmartPhone = map.get(id);
		if(SmartPhone == null) { // Chưa có trong giỏ hàng
			SmartPhone = Phone.phone.get(id);
			SmartPhone.setQty(1);
			map.put(id, SmartPhone);
		}else {
			SmartPhone.setQty(SmartPhone.getQty()+1);
		}
		return SmartPhone;
	}

	@Override
	public String remove(Integer id) {
		SmartPhone SmartPhone = map.get(id);
		if(SmartPhone==null)
		{
			return "Không Có SmartPhone Để Xóa";
		}
		else
		{
//			SP.SmartPhone.remove(SmartPhone);
//			this.remove(id);
			map.remove(id);
			return "Xóa SmartPhone thành công";
			
		}
	}

	@Override
	public SmartPhone update(Integer id, String qty) {
		SmartPhone SmartPhone = map.get(id);
		if(qty.equals("minus") && SmartPhone.getQty()>0) {
			SmartPhone.setQty(SmartPhone.getQty()-1);
			if(SmartPhone.getQty() == 0)
				this.remove(id);
		}else if(qty.equals("plus") &&SmartPhone.getQty()<100) {
			SmartPhone.setQty(SmartPhone.getQty()+1);
		}
		
		return SmartPhone;
	}

	@Override
	public void clear() {
		map.clear();
		
	}

	@Override
	public Collection<SmartPhone> getPhones() {
		return map.values();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return map.values().stream().mapToInt(SmartPhone -> SmartPhone.getQty()).sum();
	}

	@Override
	public Double getAmount() {
		// TODO Auto-generated method stub
		return map.values().stream().mapToDouble(SmartPhone->SmartPhone.getPrice()*SmartPhone.getQty()).sum();
	}
	
}
