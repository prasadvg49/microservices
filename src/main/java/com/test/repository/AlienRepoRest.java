package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Alien;

public interface AlienRepoRest extends JpaRepository<Alien, Integer>


{
	
	
}
