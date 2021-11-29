package com.htech.service;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	@Autowired
	HttpSession session;

	public <T> T get(String name) {
		return null;
	}

	

	public void set(String name, Object value) {

	}

	public void remove(String name) {
		
	}
}
