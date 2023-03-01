package com.maxtrain.prsspringboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxtrain.prsspringboot.entities.User;

import com.maxtrain.prsspringboot.entities.*;

import com.maxtrain.prsspringboot.*;

public interface UserRepository extends JpaRepository<User, Integer> {

 
	
User findByUserNameAndPassword(String username, String password);

}
