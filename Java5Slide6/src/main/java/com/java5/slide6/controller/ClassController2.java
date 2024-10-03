package com.java5.slide6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java5.slide6.dao.ClazzDAO;
import com.java5.slide6.entity.Clazz;

@Controller
public class ClassController2 {

	@Autowired
	ClazzDAO classDao;
	
	@ResponseBody
	@GetMapping("findbyname")
	List<Clazz> findbyname(){
		return classDao.FindByName("SD18402");
	}
	
	@ResponseBody
	@GetMapping("findBetween")
	List<Clazz> findBetween(){
		return classDao.FindBetwenMinAndMax(35,40);
	}
	
	@ResponseBody
	@GetMapping("findbysemesterSQL")
	List<Clazz> findbysemesterSQL(){
		return classDao.GetClassListBySemester("FALL2024");
	}
	
	@ResponseBody
	@GetMapping("findbynameandsemester")
	List<Clazz> findbynameandsemester(){
		return classDao.FindByNameAndBySemester("SD18402","FALL2024");
	}
	
	
	
	
}
