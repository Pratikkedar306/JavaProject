package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String type;
	private String place;
	private int year;
	
	public Product(int id,String name,String type,String place,int year ) {
		this.id=id;
		this.name=name;
		this.type=type;
		this.place=place;
		this.year=year;
		
	}
	public Product(){
		
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setType(String type) {
		this.type=type;
	}
	public void setPlace(String place) {
		this.place=place;
	}
	public void setYear(int year) {
		this.year=year;
	}
	public String getName() {
		return name;
	}
	public String getPlace() {
		return place;
	}
	public String getType() {
		return type;
	}
	public int getYear() {
		return year;
	}
	public int getId() {
		return id;
	}
	public String toString() {
		return "Product : { Id :"+ id +" Name : " + name+" Type : "+type +" Place : " +place+" Year : " +year+" }";
	}

}
