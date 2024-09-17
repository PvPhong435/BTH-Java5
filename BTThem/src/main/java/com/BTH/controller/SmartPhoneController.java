package com.BTH.controller;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.BTH.model.ProductForm;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class SmartPhoneController {
	
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

	@PostMapping("/UploadSmartPhone")
	public String SendInformationSmartPhone(@RequestParam("price") Float price,
											@ModelAttribute ProductForm productForm, 
											@RequestParam("image") MultipartFile image, 
											Model model
											) 
	{
		
		String name=request.getParameter("name");
		String maker = productForm.getMaker();
        String country = productForm.getCountry();
		
		 String uploadDir = servletContext.getRealPath("/uploads/");
	        File uploadDirectory = new File(uploadDir);
	        
	        // Kiểm tra nếu thư mục không tồn tại thì tạo mới
	        if (!uploadDirectory.exists()) {
	            uploadDirectory.mkdirs();
	        }
		// Xử lý file ảnh
		if (!image.isEmpty()) {
			try {
				String fileName = image.getOriginalFilename();
               String filePath = uploadDir + File.separator + fileName;
               
               // Lưu file vào thư mục uploads
               image.transferTo(new File(filePath));
				// Truyền tên file ảnh vào model để hiển thị trong view
				model.addAttribute("imageName", fileName);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			model.addAttribute("warning", "Bạn Chưa Chọn Hình Ảnh");
			return "forward:/SmartPhone";
		}
		
		if(name.isEmpty())
		{
			model.addAttribute("warning", "Bạn Chưa Nhập Tên Sản Phẩm");
			return "forward:/SmartPhone";
		}
		else if (price == null || price <= 0) {
		        model.addAttribute("warning", "Giá sản phẩm phải là số và lớn hơn 0");
		        return "forward:/SmartPhone";
		    }
		else
		{
			application.setAttribute("name",name); 
			application.setAttribute("price",price); 
			application.setAttribute("maker",maker); 
			application.setAttribute("country",country); 
			
			return "resultSmartPhone";
		}
	}
	
	
	@ModelAttribute("Makers")
	public List<String> GetMaker()
	{
		return Arrays.asList("Apple","Samsung","realme");
	}
	
	@ModelAttribute("Country")
	public List<String> GetCountry()
	{
		return Arrays.asList("USA","Chinese","Japan","VietNam");
	}
	
	@GetMapping("/SmartPhone")
	public String getMethodName(Model model) {
		return "SmartPhone";
	}
	
	
	
}
