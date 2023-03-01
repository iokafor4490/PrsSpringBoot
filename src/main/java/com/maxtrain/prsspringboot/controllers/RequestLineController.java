package com.maxtrain.prsspringboot.controllers;

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

import com.maxtrain.prsspringboot.entities.PurchaseRequest;
import com.maxtrain.prsspringboot.entities.RequestLine;
import com.maxtrain.prsspringboot.repositories.PurchaseRequestRepository;
import com.maxtrain.prsspringboot.repositories.RequestLineRepository;

@RestController
@RequestMapping("/request-lines")
public class RequestLineController {

	@Autowired
	private PurchaseRequestRepository purchaserequestrepo;

	@Autowired
	private RequestLineRepository requestLineRepo;

	@GetMapping("")
	public List<RequestLine> getAll() {
		List<RequestLine> requestline = requestLineRepo.findAll();

		return requestline;
	}

	@GetMapping("/{id}")
	public RequestLine getById(@PathVariable int id) {
		RequestLine requestLine = new RequestLine();
		Optional<RequestLine> optionalRequestLine = requestLineRepo.findById(id);

		if (optionalRequestLine.isPresent()) {
			requestLine = optionalRequestLine.get();
		}

		return requestLine;
	}

	@PostMapping("")
	public RequestLine createRequestLine(@RequestBody RequestLine newRequestLine) {
		RequestLine requestline = new RequestLine();

		boolean requestlineExists = requestLineRepo.findById(newRequestLine.getId()).isPresent();

		if (!requestlineExists) {
			requestline = requestLineRepo.save(newRequestLine);
			recalculateTotal(newRequestLine.getPurchaserequest());

		}

		return requestline;

	}

	@PutMapping("")

	public RequestLine update(@RequestBody RequestLine updatedRequestLine) {
		RequestLine requestline = new RequestLine();

		boolean requestlineExists = requestLineRepo.findById(updatedRequestLine.getId()).isPresent();

		if (requestlineExists) {

			requestline = requestLineRepo.save(updatedRequestLine);
			recalculateTotal(updatedRequestLine.getPurchaserequest());

		}

		return requestline;
	}

	@DeleteMapping("/{id}")
	public RequestLine delete(@PathVariable int id) {
		RequestLine requestline = new RequestLine();
		Optional<RequestLine> optionalRequestLine = requestLineRepo.findById(id);

		boolean requestlineExists = optionalRequestLine.isPresent();

		if (requestlineExists) {
			requestline = optionalRequestLine.get();
			requestLineRepo.deleteById(id);
			recalculateTotal(requestline.getPurchaserequest());

		}

		return requestline;
	}

	private void recalculateTotal(PurchaseRequest purchaserequest) {

		List<RequestLine> requestLines = requestLineRepo.findAllByPurchaserequest(purchaserequest);

		double total = 0;

		for (RequestLine requestLine : requestLines) {
			total += (requestLine.getProduct().getPrice() * requestLine.getQuantity());
		}
		purchaserequest.setTotal(total);

		purchaserequestrepo.save(purchaserequest);

	}

}
