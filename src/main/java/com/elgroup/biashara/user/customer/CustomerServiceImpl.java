package com.elgroup.biashara.user.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elgroup.biashara.exception.UserAlreadyExistException;
import com.elgroup.biashara.security.IRoleDAO;
import com.elgroup.biashara.security.Role;

@Service
public class CustomerServiceImpl implements ICustomerService {
	
	private String CUSTOMER_ROLE_IN_DATABASE ="CLIENT";
	
	@Autowired
	ICustomerDAO icd;
	@Autowired
	IRoleDAO ird;
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public void create(Customer customer) {
		// TODO Auto-generated method stub
		customer.setEnabled(true);
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		// Get Customer Role
		Role roleClient = ird.findByName(CUSTOMER_ROLE_IN_DATABASE);
        customer.setRole(roleClient);
		icd.save(customer);
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		icd.saveAndFlush(customer);
	}

	@Override
	public Customer getCustomer(Long id) {
		// TODO Auto-generated method stub
		return icd.getById(id);
	}

	@Override
	public void delete(Customer customer) {
		// TODO Auto-generated method stub
		icd.delete(customer);
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return icd.findAll();
	}

	@Override
	public Customer registerNewCustomerAccount(Customer customer) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		if (emailExists(customer.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + customer.getEmail());
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return icd.save(customer);
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return icd.findByEmail(email);
	}

	private boolean emailExists(final String email) {
        return icd.findByEmail(email) != null;
    }

}
