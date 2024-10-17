package com.assignment.caulong.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.caulong.models.Image;
import com.assignment.caulong.models.Product;

@Service
public class uploadService {
	
	//Upload a list of files to path
	public List<Image> uploadTo(MultipartFile[] files,String path,Product product) throws IllegalStateException, IOException {
		List<Image> images=new ArrayList<>();
		File directory= new File(path);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		int i=0;
		for(MultipartFile file:files) {
			String uploadFile=path+product.getName()+i+file.getOriginalFilename();
			Image img=new Image();
			img.setId(product.getName()+i+file.getOriginalFilename());
			img.setProduct(product);
			images.add(img);
			File des = new File(uploadFile);
			file.transferTo(des);
			i++;
		}
		return images;
	}
	//Upload a file to path
	public void uploadTo(MultipartFile file,String path,Product product) throws IllegalStateException, IOException {
		File directory= new File(path);
		if(!directory.exists()) {
			directory.mkdirs();
		}
			String uploadFile=path+file.getOriginalFilename()+product.getId();
			File des = new File(uploadFile);
			file.transferTo(des);
		}
}

