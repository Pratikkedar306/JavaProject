package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class ProductService {
	@Autowired
	ProductDB db;
	
	
	public Product addProduct(Product p) {
		return db.save(p);
	}
	
	public List<Product> getAllProduct() {
		return db.findAll();
	}
	public List<Product> getProductName(String name) {
		return db.findByNameLike(name); 
	}
	
	public List<Product> getProductType(String type) {
		return db.findByTypeLike(type); 
	}
	public List<Product> getProductPlace(String place) {
		return db.findByPlaceLike(place); 
		}
	public List<Product> getProductYear(int year) {
		return db.findByYear(year);
	}
	public void deleteProduct(int id){
		  db.deleteById(id);
	}

	public Product updateProduct(Product p, int id) {
		Optional<Product> a = db.findById(id);
		
		if(a.isPresent()) {
		Product b=a.get();
	//b.setId(p.getId());
		b.setName(p.getName());
		b.setPlace(p.getPlace());
		b.setType(p.getType());
		b.setYear(p.getYear());
		return db.save(b);
		}
		else
			return null;
		
	}

	public boolean existsById(int id) {
		// TODO Auto-generated method stub
		return db.existsById(id); 
	}
	
}
