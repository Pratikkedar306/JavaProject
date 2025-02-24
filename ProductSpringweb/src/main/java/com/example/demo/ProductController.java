package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ProductController {
	@Autowired
	ProductService sv;
	@GetMapping("/products")
	public ResponseEntity<Optional<List<Product>>> getProducts() {
		try{
			List<Product> l=sv.getAllProduct();
			if(l.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			else 
				return ResponseEntity.ok(Optional.of(l));
		}
		catch(Exception e) {
			 e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/product/add")
	public ResponseEntity<Product> addProducts(@RequestBody Product p) {
		try{
			sv.addProduct(p);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		catch(Exception e) {
			 e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		} 
	@GetMapping("/product/{name}")
	public ResponseEntity<List<Product>> getProduct(@PathVariable String name) {
		try {
			List<Product> l= sv.getProductName(name);
			if(l.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		return	ResponseEntity.ok(l); 
		}
		catch(Exception e) {
			 e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	@GetMapping("/type/{type}")
	public ResponseEntity<List<Product>> getProductType(@PathVariable String type) {
		try {
			List<Product> l=sv.getProductType(type);
			if(l.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(l);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GetMapping("/place/{place}")
	public ResponseEntity<List<Product>> getProductPlace(@PathVariable String place) {
		try {
			List<Product> l=sv.getProductPlace(place);
			if(l.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(l);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@GetMapping("/year/{year}")
	public ResponseEntity<List<Product>> getProductYear(@PathVariable int year) {
		try {
			List<Product> l=sv.getProductYear(year);
			if(l.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.ok(l);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
		try {
			if(!sv.existsById(id)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			sv.deleteProduct(id);
			return ResponseEntity.noContent().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product p, @PathVariable int id) {
		try {
			if(!sv.existsById(id)) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		 Product pr=sv.updateProduct(p,id);
	     return ResponseEntity.ok(pr);
		}
		catch(Exception e) {
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
}
