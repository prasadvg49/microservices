package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.test.entity.Register;
import com.test.model.RegisterDTO;



@Repository
public interface RegisterRepo extends JpaRepository<Register, Integer> 
{

	List<String> findByLastName(String name);
	
}