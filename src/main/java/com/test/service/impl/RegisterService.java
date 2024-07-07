package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Register;
import com.test.model.RegisterDTO;
import com.test.repository.RegisterRepo;
import com.test.service.RegisterServiceInt;

@Service
public class RegisterService implements RegisterServiceInt

{
	@Autowired
	RegisterRepo repo;
	
	public String insert(Register register)
	{
		
		Register rg = repo.save(register);
		
		if (rg != null)
		{
			List<String> lastName = repo.findByLastName("vara");
			return "success";
		}
		else {return "failure";}
		
		
		
	}
	
	


}
