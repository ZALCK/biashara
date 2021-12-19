package com.elgroup.biashara.user.moderator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.elgroup.biashara.user.User;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Moderator extends User{
	
	@Column(unique=true)
	private long phone;
	
	@Column(unique=true)
	private long idCard;
	
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
	
	@Override
	public String toString() {
		return "Moderator [phone=" + phone + ", idCard=" + idCard + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", password=" + password + ", enabled=" + enabled + "]";
	}
	
}
