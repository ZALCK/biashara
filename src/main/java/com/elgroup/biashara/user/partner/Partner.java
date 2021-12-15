package com.elgroup.biashara.user.partner;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.elgroup.biashara.product.Product;
import com.elgroup.biashara.user.User;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Partner extends User{
	private long phone;
	private long id_card;
	private String business_name;
	private String business_Logo;
	private String business_Slogan;
	
	@OneToMany(targetEntity = Product.class, mappedBy = "partner")
	private List<Product> products = new ArrayList<>();
	
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public long getId_card() {
		return id_card;
	}
	public void setId_card(long id_card) {
		this.id_card = id_card;
	}
	
	public String getBusiness_Logo() {
		return business_Logo;
	}
	public void setBusiness_Logo(String business_Logo) {
		this.business_Logo = business_Logo;
	}
	
	public String getBusiness_Slogan() {
		return business_Slogan;
	}
	public void setBusiness_Slogan(String business_Slogan) {
		this.business_Slogan = business_Slogan;
	}
	
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	
	@Override
	public String toString() {
		return "Partner [phone=" + phone + ", id_card=" + id_card + ", business_name=" + business_name
				+ ", business_Logo=" + business_Logo + ", business_Slogan=" + business_Slogan + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password + ", enabled="
				+ enabled + "]";
	}
	
}
