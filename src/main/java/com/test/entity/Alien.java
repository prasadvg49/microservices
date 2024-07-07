package com.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;



@Entity  
public class Alien 
{
	
	@Id	
	@SequenceGenerator(name = "SEQ", sequenceName = "VEHICLE_SEQ",allocationSize=1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
	
	private int id;
	private String name;
	private String lang;
	
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
/*	@Override
	public String toString() {
		return "Alien [id=" + id + ", name=" + name +  "]";
	} */

}
