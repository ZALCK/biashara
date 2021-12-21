package com.elgroup.biashara.industry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.elgroup.biashara.user.partner.Partner;

@Entity
public class Industry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;

    @OneToMany
    @JoinTable( name = "T_Industry_Partner_Associations",
                joinColumns = @JoinColumn(name = "idIndustry"),
                inverseJoinColumns = @JoinColumn(name = "idPartner") )
    private List<Partner> partners = new ArrayList<>();

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Partner> getPartners() {
		return partners;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
    
}
