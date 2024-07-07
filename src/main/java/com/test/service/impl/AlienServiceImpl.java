package com.test.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.test.entity.Alien;
import com.test.repository.AlienRepo;
import com.test.service.AlienService;

@Service
public class AlienServiceImpl implements AlienService
{
	@Autowired
	AlienRepo repo;
	public String existsByID(int id)
	{
		
		String name = repo.exists(id);
		
			if (name == null)
			{
				System.out.println("Id doesnot exists");
				return "Id doesnot exists";
				
			}
		
			else
			{
				System.out.println("Id exists");
				return name;
			}
		
		
		
		
	}
	
	public int existsByIds(int id) {
		
		int i = 0;
		
		Optional opt= repo.findById(id);
		
		if(opt.isPresent())
		{
		
			Alien alien=(Alien)opt.get();
			int output = alien.getId();
			System.out.println("output" + output);
			return output;
			
		}
		else {
			
			return i;
		}
		
		
	}
	

}
