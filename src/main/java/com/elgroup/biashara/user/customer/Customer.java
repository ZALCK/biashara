package com.elgroup.biashara.user.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.elgroup.biashara.comment.Comment;
import com.elgroup.biashara.report.Report;
import com.elgroup.biashara.user.User;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Customer extends User{
	private long phone;
	private String city;
	
	@OneToMany(targetEntity = Comment.class, mappedBy = "customer")
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(targetEntity = Report.class, mappedBy = "customer")
	private List<Report> reports = new ArrayList<>();

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	@Override
	public String toString() {
		return "Customer [phone=" + phone + ", city=" + city + ", comments=" + comments + ", reports=" + reports
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}

}
