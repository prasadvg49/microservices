package com.test.service;

import org.springframework.stereotype.Repository;

@Repository
public interface AlienService {
	
	String existsByID(int id);
	
	int existsByIds(int id);
	
	

}
