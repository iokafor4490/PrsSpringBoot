package com.maxtrain.prsspringboot.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.maxtrain.prsspringboot.entities.Product;
import com.maxtrain.prsspringboot.entities.Vendor;
import com.maxtrain.prsspringboot.repositories.ProductRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/products")


public class ProductController {

	
@Autowired
 private ProductRepository productRepo;


@GetMapping("")
public List<Product> getAll() {
	List<Product> products = productRepo.findAll();
	
	return products;
}

@GetMapping("/{id}")
public Product getById(@PathVariable int id) {
	Product product = new Product (); // new product
	Optional<Product> optionalProduct = productRepo.findById(id);
	
	if (optionalProduct.isPresent()) {
		product = optionalProduct.get();
	}
	else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	
	return product;

}

    @PostMapping("")
    public Product create(@RequestBody Product newProduct) {
	Product product = new Product();
	
	boolean productExists = productRepo.findById(newProduct.getId()).isPresent();
	
	if (!productExists ) {
		product = productRepo.save(newProduct);
	
	}
	
	return product;
}
  
    
    @PutMapping("")
  public Product update(@RequestBody Product updatedProduct) {
	  Product product = new Product();
	  
	  boolean productExists = productRepo.findById(updatedProduct.getId()).isPresent();
	  
	  if (productExists) {
		  product = productRepo.save(updatedProduct);
	  }
	  else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	  
	  return product;
  }
    
    @DeleteMapping("/{id}")
 	 public Product delete(@PathVariable int id) {
 		 Product product = new Product();
 		 Optional<Product> optionalProduct = productRepo.findById(id);
 		 
 		 
 		 boolean productExists = optionalProduct.isPresent();
 		 
 		 if (productExists) {
 		 product = optionalProduct.get();
 		 productRepo.deleteById(id);
 		 
 		 }
 		else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
 		 return product;

}
   
}

