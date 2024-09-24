package com.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class CookieService {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	
	public Cookie create(String name,String value,int days)
	{
		Cookie cookie=new Cookie(name, value);
		cookie.setMaxAge(days*60*60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}
	
	public Cookie get(String name)
	{
		Cookie[] cookies=request.getCookies();
		if(cookies!=null)
		{
			for(Cookie cookie:cookies)
			{
				if(cookie.getName().equalsIgnoreCase(name))
				{
					return cookie;
				}
			}
			
		}
		return null;
	}
	
	public String getValue(String name)
	{
		// Lấy tất cả cookie từ request
        Cookie[] cookies = request.getCookies();

        // Kiểm tra nếu có cookie trong request
        if (cookies != null) {
            // Duyệt qua danh sách cookie
            for (Cookie cookie : cookies) {
                // So sánh tên của cookie với tên được truyền vào
                if (cookie.getName().equals(name)) {
                    // Trả về giá trị của cookie nếu tìm thấy
                    return cookie.getValue();
                }
            }
        }
        // Nếu không tìm thấy cookie, trả về null hoặc một thông báo
        return "Không tìm thấy cookie!";
	}
	
	public Cookie add(String name,String value,int hours)
	{
		Cookie cookieAdd=new Cookie(name, value);
		cookieAdd.setMaxAge(hours*60*60);
		cookieAdd.setPath("/");
		response.addCookie(cookieAdd);
		return cookieAdd;
	}
	
	public void remove(String name)
	{
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie:cookies)
		{
			if(cookie.getName().equalsIgnoreCase(name))
			{
				cookie.setMaxAge(0);
				cookie.setPath("/");
			    response.addCookie(cookie);
			}
		}
	}
	
}
