package com.elgroup.biashara.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.elgroup.biashara.command.CommandLine;
import com.elgroup.biashara.comment.Comment;
import com.elgroup.biashara.user.partner.Partner;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private int price;
	private boolean inStock;
	private String image_link;
	
	@ManyToOne
    @JoinTable(name = "T_Products_Partner_Association",
    			joinColumns = @JoinColumn(name="idProduct"),
    			inverseJoinColumns = @JoinColumn(name="idPartner"))
	private Partner partner;
	
	@OneToMany(targetEntity = CommandLine.class, mappedBy = "product")
	private List<CommandLine> commandLines = new ArrayList<>();
	
	@OneToMany(targetEntity = Comment.class, mappedBy = "product")
	private List<Comment> comments = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}

	public String getImage_link() {
		return image_link;
	}

	public void setImage_link(String image_link) {
		this.image_link = image_link;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public List<CommandLine> getCommandLines() {
		return commandLines;
	}

	public void setCommandLines(List<CommandLine> commandLines) {
		this.commandLines = commandLines;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", inStock=" + inStock + ", image_link=" + image_link + ", partner=" + partner + ", commandLines="
				+ commandLines + ", comments=" + comments + "]";
	}
	
}
