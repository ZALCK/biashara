package com.elgroup.biashara.command;

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
public class Command {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date commandDate;
	
	@ManyToOne @JoinColumn(name="idCustomer", nullable=false)
    private Customer customer;
	
	@OneToMany(targetEntity=CommandLine.class, mappedBy="command", cascade=CascadeType.ALL)
    private Set<CommandLine> commandLines = new HashSet<CommandLine>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCommandDate() {
		return commandDate;
	}

	public void setCommandDate(Date commandDate) {
		this.commandDate = commandDate;
	}

	public Set<CommandLine> getCommandlines() {
		return commandLines;
	}

	public void setCommandlines(Set<CommandLine> commandlines) {
		this.commandLines = commandlines;
	}

	public String toString() {
        double totalPrice = 0;
        StringBuilder builder = new StringBuilder();
        builder.append( "Commande de >> " ).append( this.customer).append( " - " )
                        .append( this.commandDate ).append( "\n" );
        for( CommandLine theLine : this.commandLines ) {
            builder.append( "\t" ).append( theLine ).append( "\n" );
            totalPrice += theLine.getQuantity() * theLine.getProduct().getPrice();
        }
        builder.append( "    Prix total de la commande : " ).append( totalPrice ).append( " FCFA" );
        return builder.toString();
    }
	
	
}
