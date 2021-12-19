package com.elgroup.biashara.user.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerDAO extends JpaRepository<Customer, Long> {
	
	public Customer findByEmail(String email);
}
