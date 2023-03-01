package com.maxtrain.prsspringboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "RequestLine")

public class RequestLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    @ManyToOne
    @JoinColumn(name = "RequestId")
    private PurchaseRequest purchaserequest;
    
    @ManyToOne
    @JoinColumn(name = "ProductId")
    private Product product;
    
    private int quantity;

	public RequestLine() {
	}

	public RequestLine(int id, PurchaseRequest purchaserequest, Product product, int quantity) {
		this.id = id;
		this.purchaserequest = purchaserequest;
		this.product = product;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PurchaseRequest getPurchaserequest() {
		return purchaserequest;
	}

	public void setPurchaserequest(PurchaseRequest purchaserequest) {
		this.purchaserequest = purchaserequest;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "RequestLine [id=" + id + ", purchaserequest=" + purchaserequest + ", product=" + product + ", quantity="
				+ quantity + "]";
	}
    
    

}
