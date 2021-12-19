package com.elgroup.biashara.user.partner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.elgroup.biashara.product.Product;
import com.elgroup.biashara.user.User;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Partner extends User{
	
	@Column(unique=true)
	private long phone;
	
	@Column(unique=true)
	private long idCard;
	
	@Column(unique=true)
	private String businessName;
	
	private String businessLogo;
	private String businessSlogan;
	
	@OneToMany(targetEntity = Product.class, mappedBy = "partner")
	private List<Product> products = new ArrayList<>();
	
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public long getIdCard() {
		return idCard;
	}
	public void setIdCard(long idCard) {
		this.idCard = idCard;
	}
	
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	public String getBusinessLogo() {
		return businessLogo;
	}
	public void setBusinessLogo(String businessLogo) {
		this.businessLogo = businessLogo;
	}
	
	public String getBusinessSlogan() {
		return businessSlogan;
	}
	public void setBusinessSlogan(String businessSlogan) {
		this.businessSlogan = businessSlogan;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return "Partner [phone=" + phone + ", idCard=" + idCard + ", businessName=" + businessName + ", businessLogo="
				+ businessLogo + ", businessSlogan=" + businessSlogan + ", products=" + products + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password + ", enabled="
				+ enabled + "]";
	}
	
	
}
