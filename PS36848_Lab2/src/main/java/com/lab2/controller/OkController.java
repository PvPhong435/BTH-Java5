package com.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletContext;


@Controller
public class OkController {
	
	@Autowired
	ServletContext application;
	
	 @RequestMapping(value = "/ok", method = RequestMethod.POST)
	    public String m1() {
		 application.setAttribute("message", "you trust call method m1");
	        return "ok";
	    }
	    
	    @RequestMapping(value = "/ok", method = RequestMethod.GET)
	    public String m2() {
	    	application.setAttribute("message", "you trust call method m2");
	        return "ok";
	    }
	    
	    @RequestMapping(value = "/ok", params = "x")
	    public String m3() {
	    	application.setAttribute("message", "you trust call method m3");
	        return "ok";
	    }
	
}
