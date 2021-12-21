package com.elgroup.biashara.user.partner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.elgroup.biashara.industry.Industry;
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
	
	@OneToOne
	@JoinTable( name = "T_Industry_Partner_Associations",
    joinColumns = @JoinColumn(name = "idPartner"),
    inverseJoinColumns = @JoinColumn(name = "idIndustry") )
	private Industry industry;
	
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
	
	public Industry getIndustry() {
		return industry;
	}
	public void setIndustry(Industry industry) {
		this.industry = industry;
	}
	
	@Override
	public String toString() {
		return "Partner [phone=" + phone + ", idCard=" + idCard + ", businessName=" + businessName + ", businessLogo="
				+ businessLogo + ", businessSlogan=" + businessSlogan + ", Number of products=" + products.size() + ", industry="
				+ industry.getName() + ", firstname=" + firstname + ", lastname=" + lastname + ", password=" + password
				+ ", email=" + email + ", resetPasswordToken=" + resetPasswordToken + ", enabled=" + enabled + "]";
	}
	
}
