package com.elgroup.biashara.report;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.elgroup.biashara.product.Product;
import com.elgroup.biashara.user.customer.Customer;

@Entity
public class Report {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date reportDate;
	
	@ManyToOne
	@JoinTable(name = "T_Reports_Customer_Association",
	joinColumns = @JoinColumn(name="idReport"),
	inverseJoinColumns = @JoinColumn(name="idCustomer"))
	private Customer customer;
	
	@ManyToOne
	@JoinTable(name = "T_Reports_Product_Association",
	joinColumns = @JoinColumn(name="idReport"),
	inverseJoinColumns = @JoinColumn(name="idProduct"))
	private Product product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", reportDate=" + reportDate + ", customer=" + customer + ", product=" + product
				+ "]";
	}
	
}
