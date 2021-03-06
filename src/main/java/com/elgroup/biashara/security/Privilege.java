package com.elgroup.biashara.security;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Privilege {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
    private String name;

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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", name=" + name + ", creationDate=" + creationDate + "]";
	}

}
