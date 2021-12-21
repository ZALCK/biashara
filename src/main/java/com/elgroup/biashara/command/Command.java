package com.elgroup.biashara.command;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.elgroup.biashara.user.customer.Customer;

@Entity
public class Command {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne @JoinColumn(name="idCustomer", nullable=false)
    private Customer customer;
	
	@OneToMany(targetEntity=CommandLine.class, mappedBy="command", cascade=CascadeType.ALL)
    private Set<CommandLine> commandLines = new HashSet<CommandLine>();

	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
    private Date creationDate;
    
    @PrePersist
    public void onCreate() {
		creationDate = new Date();
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<CommandLine> getCommandLines() {
		return commandLines;
	}

	public void setCommandLines(Set<CommandLine> commandLines) {
		this.commandLines = commandLines;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String toString() {
        double totalPrice = 0;
        StringBuilder builder = new StringBuilder();
        builder.append( "Commande de >> " ).append( this.customer).append( " - " )
                        .append( this.creationDate ).append( "\n" );
        for( CommandLine theLine : this.commandLines ) {
            builder.append( "\t" ).append( theLine ).append( "\n" );
            totalPrice += theLine.getQuantity() * theLine.getProduct().getPrice();
        }
        builder.append( "    Prix total de la commande : " ).append( totalPrice ).append( " FCFA" );
        return builder.toString();
    }	
	
}
