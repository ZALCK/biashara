package com.elgroup.biashara.user.moderator;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.elgroup.biashara.user.User;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Moderator extends User{
	private long phone;
	private long id_card;
	
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
	
	@Override
	public String toString() {
		return "Moderator [phone=" + phone + ", id_card=" + id_card + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", password=" + password + ", enabled=" + enabled + "]";
	}
	
}
