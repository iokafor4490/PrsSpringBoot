package com.maxtrain.prsspringboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maxtrain.prsspringboot.entities.PurchaseRequest;


public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Integer> {
	
  List<PurchaseRequest> findByStatusAndUserIdNot(String status, int userId);

}
