package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Register;
import com.test.model.RegisterDTO;
import com.test.service.RegisterServiceInt;
//@Controller
@RestController
public class RegisterContoller {
	
	
	@Autowired
	RegisterServiceInt service;

	@PostMapping(path="/addRegister",consumes = "application/json")
	public String register(@RequestBody Register register)
		{
		
		service.insert(register);
		
		return "success";
		}
	

	}


