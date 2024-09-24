package com.lab4.service;

import java.net.http.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;

public class SessionService {
	@Autowired
	HttpSession session;
}
