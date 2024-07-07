package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.test.entity.Alien;

public interface AlienRepo extends CrudRepository<Alien, Integer> 
{

	List<Alien> findByLang(String lang);
	
	List<Alien> findByName(String name);
	
	@Query("from Alien where name=?1 order by name")
	List<Alien> findByNameSorted(String name);
	
	@Query("select name from Alien where id = ?1") 
	String exists(int id);
	
	
}
