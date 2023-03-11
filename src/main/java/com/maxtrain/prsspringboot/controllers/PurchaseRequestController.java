package com.maxtrain.prsspringboot.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxtrain.prsspringboot.entities.Product;
import com.maxtrain.prsspringboot.entities.PurchaseRequest;
import com.maxtrain.prsspringboot.repositories.PurchaseRequestRepository;

@RestController
@RequestMapping("/purchaserequests")


public class PurchaseRequestController {
	
	private final String NEW = "New";
	private final String REVIEW = "Review";
	private final String APPROVED = "Approved";
	private final String REJECTED = "Rejected";
	private final String REOPENED = "Reopened";

	
@Autowired
private PurchaseRequestRepository purchaserequestRepo;


@GetMapping("")
public List<PurchaseRequest> getAll() {
	List<PurchaseRequest> purchaserequests = purchaserequestRepo.findAll();
	
	return purchaserequests;
	
}

@GetMapping("/{id}")
public PurchaseRequest getById(@PathVariable int id) {
	PurchaseRequest purchaserequest = new PurchaseRequest (); // new purchase request
	Optional<PurchaseRequest> optionalPurchaseRequest = purchaserequestRepo.findById(id);
	
	if (optionalPurchaseRequest.isPresent()) {
		purchaserequest = optionalPurchaseRequest.get();
	}
	
	return purchaserequest;


}

    @PostMapping("")
    public PurchaseRequest createPurchaseRequest(@RequestBody PurchaseRequest newPurchaseRequest) {
	PurchaseRequest purchaserequest = new PurchaseRequest();
	
	boolean purchaserequestExists = purchaserequestRepo.findById(newPurchaseRequest.getId()).isPresent();
	
	if (!purchaserequestExists ) {
		
		purchaserequest.setStatus(NEW);
		purchaserequest.setSubmittedDate(LocalDateTime.now());
		
		
		purchaserequest = purchaserequestRepo.save(newPurchaseRequest);
	
	
	}
	
	
	return purchaserequest;
	
}
	

     @PutMapping("")
     public PurchaseRequest update (@RequestBody PurchaseRequest updatedPurchaseRequest) {
    	 
    	 PurchaseRequest purchaserequest = new PurchaseRequest();
    	 
     boolean purchaserequestExists = purchaserequestRepo.findById(updatedPurchaseRequest.getId()).isPresent();
     
     if (purchaserequestExists) {
    	 purchaserequest = purchaserequestRepo.save(updatedPurchaseRequest);
     }
     
        return purchaserequest;
     
   

}
     
     @DeleteMapping("/{id}")
 	 public PurchaseRequest delete(@PathVariable int id) {
 		 PurchaseRequest purchaserequest = new PurchaseRequest();
 		 Optional<PurchaseRequest> optionalPurchaseRequest = purchaserequestRepo.findById(id);
 		 
 		 
 		 boolean purchaserequestExists = optionalPurchaseRequest.isPresent();
 		 
 		 if (purchaserequestExists) {
 		 purchaserequest = optionalPurchaseRequest.get();
 		 purchaserequestRepo.deleteById(id);
 		 
 		 }
 		 
 		 return purchaserequest;
     



}
     @GetMapping("/list-review/{userId}")
     public List<PurchaseRequest> getAllForReview(@PathVariable int userId) {
     List<PurchaseRequest> purchaserequest = purchaserequestRepo.findByStatusAndUserIdNot(REVIEW, userId);
    	 
    	 return purchaserequest;
     
} 
    @PutMapping("/approve")
   public PurchaseRequest approve(@RequestBody PurchaseRequest approvedPurchaseRequest) {
	   PurchaseRequest purchaserequest = new PurchaseRequest();
	   
	   boolean purchaserequestExists = purchaserequestRepo.existsById(approvedPurchaseRequest.getId());
	   
			   
		if (purchaserequestExists ) {
			
		approvedPurchaseRequest.setStatus(APPROVED);
		
	
		purchaserequest = purchaserequestRepo.save(approvedPurchaseRequest);
		
		}
	  
	   
	   return purchaserequest;
	   
	   
   }
    
    @PutMapping("/review")
    public PurchaseRequest submitforReview(@RequestBody PurchaseRequest purchaserequestforReview) {
    	PurchaseRequest purchaserequest = new PurchaseRequest();
    	boolean purchaserequestExists = purchaserequestRepo.existsById(purchaserequestforReview.getId());
     
    	if (purchaserequestExists && purchaserequestforReview.getTotal() <= 50) {
    		purchaserequestforReview.setStatus(APPROVED);
    		purchaserequestforReview.setSubmittedDate(LocalDateTime.now());
    		purchaserequest = purchaserequestRepo.save(purchaserequestforReview);
    	
    	} else if (purchaserequestExists && purchaserequestforReview.getTotal() >= 50) {
    		purchaserequestforReview.setStatus(REVIEW);
    		purchaserequestforReview.setSubmittedDate(LocalDateTime.now());
    		purchaserequest = purchaserequestRepo.save(purchaserequestforReview);
    		
    	}
    	
    	 return purchaserequest;
    	
    }
   
    
    @PutMapping("/reject")
    public PurchaseRequest reject(@RequestBody PurchaseRequest rejectedPurchaseRequest) {
 	   PurchaseRequest purchaserequest = new PurchaseRequest();
 	   
 	   boolean purchaserequestExists = purchaserequestRepo.existsById(rejectedPurchaseRequest.getId());
 	   
 			   
 		if (purchaserequestExists ) {
 			
 		rejectedPurchaseRequest.setStatus(REJECTED);
 		
 	
 		purchaserequest = purchaserequestRepo.save(rejectedPurchaseRequest);
 		
 		}
 	  
 	   
 	   return purchaserequest;  
     
     
}
    
    @PutMapping("/reopen")
    public PurchaseRequest reopen(@RequestBody PurchaseRequest reopenedPurchaseRequest) {
  	   PurchaseRequest purchaserequest = new PurchaseRequest();
  	   
  	 boolean purchaserequestExists = purchaserequestRepo.existsById(reopenedPurchaseRequest.getId());
  	 
  	if (purchaserequestExists ) {
		
		reopenedPurchaseRequest.setStatus(REOPENED);
		
	
		purchaserequest = purchaserequestRepo.save(reopenedPurchaseRequest);
    
    
   }
  	
  	return purchaserequest;
  			
  			
   }
    
   
}
    
