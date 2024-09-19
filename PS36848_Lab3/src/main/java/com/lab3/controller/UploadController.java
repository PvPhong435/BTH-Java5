package com.lab3.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lab3.model.Student2;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UploadController {

	@Autowired
	private ServletContext servletContext;
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession session;
	@Autowired
	ServletContext application;

	@RequestMapping("/student/formUpload")
	public String requestMethodName(Model model) {
		Student2 student = new Student2();
		model.addAttribute("sv2", student);
		return "formUpload";
	}

	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
	    }
	
	@RequestMapping("/student/SaveFormUpload")
	public String save(@ModelAttribute("sv2") Student2 sv, Model model, @RequestParam("images") MultipartFile image) {
		String uploadDir = servletContext.getRealPath("/uploads/");
		File uploadDirectory = new File(uploadDir);

		// Kiểm tra nếu thư mục không tồn tại thì tạo mới
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
		}
		if (!image.isEmpty()) {
			try {
				String fileName = image.getOriginalFilename();
				String filePath = uploadDir + File.separator + fileName;

				// Lưu file vào thư mục uploads
				image.transferTo(new File(filePath));
				// Truyền tên file ảnh vào model để hiển thị trong view
//					model.addAttribute("imageName", fileName);

				sv.setImage("/uploads/" + fileName);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(sv.getName());
		System.out.println(sv.getEmail());
		System.out.println(sv.getMarks());
		System.out.println(sv.getGender());
		System.out.println(sv.getHobbies());
		model.addAttribute("sv2", sv);
		return "resultUpload";
	}

	@ModelAttribute("genders")
	public List<String> getGenders() {
//		Map<Boolean, String> map = new HashMap<Boolean, String>();
//		map.put(true, "Male");
//		map.put(false, "Female");
		
		

		return Arrays.asList("Male", "Female");
	}

	@ModelAttribute("faculties")
	public List<String> getFaculties() {
		return Arrays.asList("CNTT", "DLNHKS", "QTDN");
	}

	@ModelAttribute("hobbies")
	public List<String> getHobbies() {
//		Map<String, String> map = new HashMap<>();
//		map.put("T", "Travelling");
//		map.put("M", "Music");
//		map.put("F", "Food");
//		map.put("O", "Other");
		return Arrays.asList("Travelling", "Music", "Food","Other");
	}
}
