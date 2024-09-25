package com.lab4.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.lab4.Model.Item;
import com.lab4.util.SP;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	Map<Integer, Item> map = new HashMap<>();
	
	@Override
	public Item add(Integer id) {
		Item item = map.get(id);
		if(item == null) { // Chưa có trong giỏ hàng
			item = SP.item.get(id);
			item.setQty(1);
			map.put(id, item);
		}else {
			item.setQty(item.getQty()+1);
		}
		return item;
	}

	@Override
	public String remove(Integer id) {
		Item item = map.get(id);
		if(item==null)
		{
			return "Không Có Item Để Xóa";
		}
		else
		{
//			SP.item.remove(item);
			this.remove(id);
			map.remove(id);
			return "Xóa item thành công";
			
		}
	}

	@Override
	public Item update(Integer id, String qty) {
		Item item = map.get(id);
		if(qty.equals("minus") && item.getQty()>0) {
			item.setQty(item.getQty()-1);
			if(item.getQty() == 0)
				this.remove(id);
		}else if(qty.equals("plus") &&item.getQty()<100) {
			item.setQty(item.getQty()+1);
		}
		
		return item;
	}

	@Override
	public void clear() {
		map.clear();
		
	}

	@Override
	public Collection<Item> getItems() {
		return map.values();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return map.values().stream().mapToInt(item -> item.getQty()).sum();
	}

	@Override
	public Double getAmount() {
		// TODO Auto-generated method stub
		return map.values().stream().mapToDouble(item->item.getPrice()*item.getQty()).sum();
	}
	
}
