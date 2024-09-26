package com.lab4.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.lab4.Model.SmartPhone;

import jakarta.servlet.ServletContext;

public class Phone {
	public static Map<Integer, SmartPhone> phone = new HashMap<>();
	static {
		phone.put(1, new SmartPhone(1, "Iphone 11", 13000.0, "8gb", "128gb",
				new String[] { "iPhone13.jpg", "iphone14.jpg", "iphone-15.png" }, "IOS", "the iphone 11 ", 1));
		phone.put(2, new SmartPhone(2, "Iphone 12", 14000.0, "8gb", "128gb",
				new String[] { "iphone14.jpg", "iPhone13.jpg", "iphone-15.png" }, "IOS", "the iphone 12 ", 1));
		phone.put(3, new SmartPhone(3, "Iphone 13", 15000.0, "8gb", "128gb",
				new String[] { "iphone-15.png", "iphone14.jpg", "iPhone13.jpg" }, "IOS", "the iphone 13 ", 1));
		phone.put(4, new SmartPhone(4, "Iphone 14", 16000.0, "8gb", "128gb",
				new String[] { "iPhone13.jpg", "iphone-15.png", "iphone14.jpg" }, "IOS", "the iphone 14 ", 1));
	}

	@Autowired
	static ServletContext servletContext;
	
	static MultipartFile image;

	public static Map<Integer, SmartPhone> getFromTXT() {
		Map<Integer, SmartPhone> listSmartPhone = new HashMap<>();
		String uploadDir = servletContext.getRealPath("/uploads/");
		File file = new File(uploadDir, "productNote.txt");
		if (!file.exists()) {

			return null;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				// Đọc các thuộc tính của sản phẩm từ tệp
				if (!line.equals("")) {
					int id = Integer.parseInt(line);
					String name = br.readLine();
					Double price = Double.parseDouble(br.readLine());
					String ram = br.readLine();
					String rom = br.readLine();
					String[] image = br.readLine().split(",");
					String operatingSystem = br.readLine();
					String descripe = br.readLine();
					// Tạo đối tượng Products và thêm vào danh sách
					SmartPhone phone = new SmartPhone(id, name, price, ram, rom, new String[] { "haha" },
							operatingSystem, descripe, 1);
					listSmartPhone.put(id, phone);
				}
			}
			return listSmartPhone;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Boolean addToTXT(SmartPhone phone) {
		String uploadDir = servletContext.getRealPath("/uploads/");
		File uploadDirectory = new File(uploadDir);
		if (!uploadDirectory.exists()) {
			uploadDirectory.mkdirs();
		}
		if (!phone.getImage()[0].isEmpty()) {
			try {
				String filePath;
				for (String imgPhone : phone.getImage()) {
					filePath = uploadDir + File.separator + imgPhone;
					// Lưu file vào thư mục uploads
					image.transferTo(new File(filePath));
				}
				filePath = uploadDir + "productNote.txt";
				try (FileOutputStream fos = new FileOutputStream(filePath, true)) {
					String productInfo = phone.getId() + "\n" + 
										 phone.getName() + "\n" + 
										 phone.getPrice() + "\n"+
										 phone.getRam() + "\n" + 
										 phone.getRom() + "\n" + 
										 phone.getImage() + "\n"+
										 phone.getOperatingSystem() + "\n" + 
										 phone.getDescripe() + "\n\n";

					fos.write(productInfo.getBytes());
				} catch (Exception e) {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}
