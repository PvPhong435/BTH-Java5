package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.model.Users;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class login {

	private static String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession session;
	@Autowired
	ServletContext application;

	@PostMapping("/upload")
	public String handleFormSubmit(

//			@RequestParam("username") String username,
//            @RequestParam("password") String password,
			@RequestParam("image") MultipartFile image, Model model) {
		String username, password;
		username = request.getParameter("username");
		
		password = request.getParameter("password");

		Users user = new Users(username,password,image.toString());

		File uploadDir = new File(UPLOAD_DIR);
		if (!uploadDir.exists()) {
			uploadDir.mkdir(); // Tạo thư mục nếu chưa tồn tại
		}
		// Xử lý file ảnh
		if (!image.isEmpty()) {
			try {
				// Lấy tên file gốc
				String fileName = image.getOriginalFilename();
				// Đường dẫn nơi lưu trữ ảnh
				Path path = Paths.get(UPLOAD_DIR + fileName);
				// Lưu file ảnh
				image.transferTo(new File(path.toString()));
				// Truyền tên file ảnh vào model để hiển thị trong view
				model.addAttribute("imageName", fileName);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		model.addAttribute("username", username);
		application.setAttribute("password",request.getParameter("password")); 
		return "result";
	}

}
