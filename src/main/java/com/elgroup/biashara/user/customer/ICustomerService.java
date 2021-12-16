package com.elgroup.biashara.user.customer;

import java.util.List;

import com.elgroup.biashara.exception.UserAlreadyExistException;

public interface ICustomerService {
	public void create (Customer customer);
	public void update (Customer customer);
	public Customer getCustomer (Long id);
	public void delete (Customer customer);
	public List<Customer> getAll();
	
	public Customer registerNewCustomerAccount(Customer customer) throws UserAlreadyExistException;
	public Customer findByEmail(String email);
}
