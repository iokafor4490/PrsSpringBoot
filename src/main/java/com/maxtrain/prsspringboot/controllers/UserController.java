package com.maxtrain.prsspringboot.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.maxtrain.prsspringboot.entities.User;
import com.maxtrain.prsspringboot.repositories.UserRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserRepository userRepo;

	@GetMapping("")
	public List<User> getAll() {
		List<User> users = userRepo.findAll();
		
		 return users;
	}
	
//	@GetMapping("/{id}")
//	public User getById(@PathVariable int id) {
//		User user = new User(); // new user
//		Optional<User> optionalUser = userRepo.findById(id);
//		
//		if (optionalUser.isPresent()) {
//			user = optionalUser.get();
//		}
//		return user;
//		
//		
//	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> getById(@PathVariable int id) {
	   Optional<User> optionalUser = userRepo.findById(id);
       if (!optionalUser.isPresent()) {
    	   // Return a 404 response if a user with the specified ID was not found
 return ResponseEntity.notFound().build(); }
 User user = optionalUser.get();
 	return ResponseEntity.ok(user);
 	}
	
	@PostMapping("")
	public User create(@RequestBody User newUser) {
		User user = new User();
		
		boolean userExists = userRepo.findById(newUser.getId()).isPresent();
		
		
		if (!userExists ) {
			user = userRepo.save(newUser);
		
		}
		
		return user;
		
		
		
		
	}
	
	 @PutMapping("")
	 public User update(@RequestBody User updatedUser) {
		 User user = new User();
		 
		 boolean userExists = userRepo.findById(updatedUser.getId()).isPresent();
		
		 
		 if (userExists) {
			 user = userRepo.save(updatedUser);
		 
		 }
		 else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		 
		 
		 return user;
		 
	}
	 
	 @DeleteMapping("{id}")
	 public User delete(@PathVariable int id) {
		 User user = new User();
		 Optional<User> optionalUser = userRepo.findById(id);
		 
		 
		 boolean userExists = optionalUser.isPresent();
		 
		 if (userExists) {
		 user = optionalUser.get();
		 userRepo.deleteById(id);
		 
		 } 
		 else throw new ResponseStatusException(HttpStatus.NOT_FOUND); //exception return new ResponseEntity(HttpStatus.NOT_FOUND);
		 
		 return user;
	 
	 }

	 @PostMapping("/login")
	 public User authenticate(@RequestBody User loginUser) {
     User user = new User();
	user = userRepo.findByUserNameAndPassword(loginUser.getUserName(), loginUser.getPassword());
		
		 return user;
	 }

}


