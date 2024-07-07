package com.test.service;

import org.springframework.stereotype.Service;

import com.test.entity.Register;
import com.test.model.RegisterDTO;
@Service
public interface RegisterServiceInt 
{
	
	String insert(Register register);

}
