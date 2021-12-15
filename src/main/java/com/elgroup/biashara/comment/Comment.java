package com.elgroup.biashara.comment;

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
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date commentDate;
	
	@ManyToOne
	@JoinTable(name = "T_Comments_Customer_Association",
	joinColumns = @JoinColumn(name="idComment"),
	inverseJoinColumns = @JoinColumn(name="idCustomer"))
	private Customer customer;
	
	@ManyToOne
	@JoinTable(name = "T_Comments_Product_Association",
	joinColumns = @JoinColumn(name="idComment"),
	inverseJoinColumns = @JoinColumn(name="idProduct"))
	private Product product;
	
	private String text;
	private boolean isVisible;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getCommentDate() {
		return commentDate;
	}
	
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
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
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentDate=" + commentDate + ", customer=" + customer + ", product=" + product
				+ ", text=" + text + ", isVisible=" + isVisible + "]";
	}
	
}
