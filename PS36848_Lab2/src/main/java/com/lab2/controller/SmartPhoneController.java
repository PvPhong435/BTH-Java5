package com.lab2.controller;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lab2.model.ProductForm;
import com.lab2.model.Products;

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
	public String SendInformationSmartPhone(@RequestParam("price") Float price, @ModelAttribute ProductForm productForm,
			@RequestParam("image") MultipartFile image, Model model) {

		String name = request.getParameter("name");
		String maker = productForm.getMaker();
		String country = productForm.getCountry();
		String fileName;
		String filePath;
		List<Products> productList=new ArrayList<Products>();

		String uploadDir = servletContext.getRealPath("/uploads/");
		File uploadDirectory = new File(uploadDir);

		// Kiểm tra nếu thư mục không tồn tại thì tạo mới
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
		}
		// Xử lý file ảnh
		if (!image.isEmpty()) {
			try {
				fileName = image.getOriginalFilename();
				filePath = uploadDir + File.separator + fileName;

				// Lưu file vào thư mục uploads
				image.transferTo(new File(filePath));
				// Truyền tên file ảnh vào model để hiển thị trong view
				model.addAttribute("imageName", fileName);
				filePath = uploadDir + "productNote.txt";
//				String encodedImage = "";
//				 try {
//		                byte[] imageBytes = image.getBytes();
//		                encodedImage = Base64.getEncoder().encodeToString(imageBytes);
//		            } catch (IOException e) {
//		                model.addAttribute("warning", "Failed to encode image: " + e.getMessage());
//		                return "SmartPhone";
//		            }
				 try (FileOutputStream fos = new FileOutputStream(filePath, true)) {
			            String productInfo = name + "\n" +
			                                 price + "\n" +
			                                 maker + "\n" +
			                                 country + "\n" +
			                                 fileName + "\n\n";

			            fos.write(productInfo.getBytes());

			            model.addAttribute("message", "Product saved successfully!");
			            model.addAttribute("name", name);
			            model.addAttribute("price", price);
			            model.addAttribute("maker", maker);
			            model.addAttribute("country", country);

			        } catch (IOException e) {
			            model.addAttribute("message", "Failed to save product: " + e.getMessage());
			        }
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		 File file = new File(uploadDir, "productNote.txt");
		 if (!file.exists()) {
	            model.addAttribute("message", "File not found.");
	            return "SmartPhone"; 
	        }
		 try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                // Đọc các thuộc tính của sản phẩm từ tệp
	            	if(!line.equals(""))
	            	{
	            		String name1 = line;
		                String price1 = br.readLine();
		                String maker1 = br.readLine();
		                String country1 = br.readLine();
		                String image1 = br.readLine();
		                // Tạo đối tượng Products và thêm vào danh sách
		                Products product = new Products(name1, price1, maker1, country1, image1);
		                productList.add(product);
	            	}
	                

	               
	                
	               
	            }
	            application.setAttribute("productList", productList);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 	
		 
		 
//		application.setAttribute("name", name);
//		application.setAttribute("price", price);
//		application.setAttribute("maker", maker);
//		application.setAttribute("country", country);

		return "resultSmartPhone";

	}

	@ModelAttribute("Makers")
	public List<String> GetMaker() {
		return Arrays.asList("Apple", "Samsung", "realme");
	}

	@ModelAttribute("Country")
	public List<String> GetCountry() {
		return Arrays.asList("USA", "Chinese", "Japan", "VietNam");
	}

	@GetMapping("/SmartPhone")
	public String getMethodName(Model model) {
		return "SmartPhone";
	}

}
