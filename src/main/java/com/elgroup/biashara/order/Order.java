package com.elgroup.biashara.order;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.elgroup.biashara.user.customer.Customer;

@Entity
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
	@ManyToOne @JoinColumn(name="idCustomer", nullable=false)
    private Customer customer;
	
	@OneToMany(targetEntity=OrderLine.class, mappedBy="order", cascade=CascadeType.ALL)
    private Set<OrderLine> orderLines = new HashSet<OrderLine>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Set<OrderLine> getOrderlines() {
		return orderLines;
	}

	public void setOrderlines(Set<OrderLine> orderlines) {
		this.orderLines = orderlines;
	}

	public String toString() {
        double totalPrice = 0;
        StringBuilder builder = new StringBuilder();
        builder.append( "Commande de >> " ).append( this.customer).append( " - " )
                        .append( this.orderDate ).append( "\n" );
        for( OrderLine theLine : this.orderLines ) {
            builder.append( "\t" ).append( theLine ).append( "\n" );
            totalPrice += theLine.getQuantity() * theLine.getProduct().getPrice();
        }
        builder.append( "    Prix total de la commande : " ).append( totalPrice ).append( " FCFA" );
        return builder.toString();
    }
	
	
}
