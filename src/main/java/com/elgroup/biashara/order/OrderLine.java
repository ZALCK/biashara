package com.elgroup.biashara.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.elgroup.biashara.product.Product;

@Entity
public class OrderLine {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idOrderLine;

    @ManyToOne
    @JoinColumn(name="idOrder", nullable=false)
    private Order order;

    @ManyToOne()
    @JoinColumn(name="idProduct", nullable=false)
    private Product product;

    private int quantity;
    
    private String statut;

	public OrderLine() {
		super();
	}

	public OrderLine(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}

	public OrderLine(Order order, Product product, int quantity) {
		super();
		this.order = order;
		this.product = product;
		this.quantity = quantity;
	}

	public int getIdOrderLine() {
		return idOrderLine;
	}

	public void setIdOrderLine(int idOrderLine) {
		this.idOrderLine = idOrderLine;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "OrderLine [idOrderLine=" + idOrderLine + ", order=" + order + ", product=" + product + ", quantity="
				+ quantity + ", statut=" + statut + "]";
	}

}
