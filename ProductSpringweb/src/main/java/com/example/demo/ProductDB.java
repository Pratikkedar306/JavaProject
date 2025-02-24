package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductDB extends JpaRepository<Product,Integer>{
		 List<Product> findByNameLike(String name);
		 List<Product> findByTypeLike(String type);
		 List<Product> findByYear(int year);
		 List<Product> findByPlaceLike(String place);
		

		 
}
