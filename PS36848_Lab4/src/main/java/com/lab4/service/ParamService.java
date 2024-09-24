package com.lab4.service;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;
	
	public String getString(String name,String defaultValue)
	{
		if(name==null||name=="")
		{
			return defaultValue;
		}
		else
		{
			String value=request.getParameter(name);
			return value;
		}
	}
	
	public int getInt(String name,int defaultValue)
	{
		try {
			
				if(name==null||name=="")
				{
					return defaultValue;
				}
				else
				{
					int n=Integer.parseInt(request.getParameter(name));
					return n;
				}
			
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public Double getDouble(String name,Double defaultValue)
	{
		try {
			if(name==null||name=="")
			{
				return defaultValue;
			}
			else
			{
				Double n=Double.parseDouble(request.getParameter(name));
				return n;
			}
			
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public Boolean getBoolean(String name,boolean defaultValue)
	{
		try {
			if(name==null||name=="")
			{
				return defaultValue;
			}
			else
			{
				Boolean bool=Boolean.parseBoolean(request.getParameter(name));
				return bool;
			}
			
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	public Date getDate(String name,Date defaultValue)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			if(name==null||name=="")
			{
				return defaultValue;
			}
			else
			{
				Date date=formatter.parse(name);
				return date;
			}
			
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	@Autowired
	ServletContext app;
	
	public File save(MultipartFile file,String path)
	{
		File dir=new File(app.getRealPath(path));
		if(!dir.exists())
		{
			dir.mkdirs();
		}
		try {
			
			File saveFile=new File(dir,file.getOriginalFilename());
			file.transferTo(saveFile);
			return saveFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
