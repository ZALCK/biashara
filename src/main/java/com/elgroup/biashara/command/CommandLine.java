package com.elgroup.biashara.command;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.elgroup.biashara.product.Product;

@Entity
public class CommandLine {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

    @ManyToOne
    @JoinColumn(name="idCommand")
    private Command command;

    @ManyToOne()
    @JoinColumn(name="idProduct")
    private Product product;

    private int quantity;
    
    private String state;

	public CommandLine() {
		super();
		this.state = CommandState.PENDING.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "CommandLine [id=" + id + ", command=" + command + ", product=" + product + ", quantity=" + quantity
				+ ", state=" + state + "]";
	}

}
