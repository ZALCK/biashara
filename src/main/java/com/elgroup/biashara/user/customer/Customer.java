package com.elgroup.biashara.user.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.elgroup.biashara.command.Command;
import com.elgroup.biashara.comment.Comment;
import com.elgroup.biashara.report.Report;
import com.elgroup.biashara.user.User;

@Entity
@PrimaryKeyJoinColumn(name = "ID")
public class Customer extends User{
	
	@Column(unique=true)
	private long phone;
	
	private String city;
	
	@OneToMany(targetEntity = Comment.class, mappedBy = "customer")
	private List<Comment> comments = new ArrayList<>();
	
	@OneToMany(targetEntity = Report.class, mappedBy = "customer")
	private List<Report> reports = new ArrayList<>();
	
	@OneToMany(targetEntity = Command.class, mappedBy = "customer")
	private List<Command> commands = new ArrayList<>();

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

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}

	@Override
	public String toString() {
		return "Customer [phone=" + phone + ", city=" + city + ", comments=" + comments + ", reports=" + reports
				+ ", commands=" + commands + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", enabled=" + enabled + "]";
	}

}
