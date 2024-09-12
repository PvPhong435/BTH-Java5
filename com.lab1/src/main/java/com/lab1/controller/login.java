package com.lab1.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class login {
	
	@PostMapping("/upload")
	public String handleFormSubmit(
			@RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("image") MultipartFile image,
            org.springframework.ui.Model model)
	{
		
		// Xử lý file ảnh
		 if (!image.isEmpty()) {
	            try {
	                byte[] bytes = image.getBytes();
	                // Có thể lưu file hoặc xử lý ở đây
	                String fileName = image.getOriginalFilename();
	                model.addAttribute("imageName", fileName);  // Lưu tên ảnh vào model để hiển thị
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		 model.addAttribute("username", username);
	     model.addAttribute("password", password);  // Chỉ ví dụ, không nên hiển thị mật khẩu thật
	        return "result";
	}
	
}
