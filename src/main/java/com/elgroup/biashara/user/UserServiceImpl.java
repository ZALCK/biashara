package com.elgroup.biashara.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elgroup.biashara.exception.UserAlreadyExistException;
import com.elgroup.biashara.security.IRoleDAO;
import com.elgroup.biashara.security.Role;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDAO isd;
	@Autowired
	IRoleDAO ird;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void create(User user) {
		// TODO Auto-generated method stub
		isd.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		isd.saveAndFlush(user);
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return isd.getById(id);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		isd.delete(user);;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return isd.findAll();
	}

	@Override
	public User registerNewUserAccount(User user) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return isd.save(user);
	}
	
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return isd.findByEmail(email);
	}
	
	private boolean emailExists(final String email) {
        return isd.findByEmail(email) != null;
    }

	@Override
	public Role getRoleByName(String name) {
		// TODO Auto-generated method stub
		return ird.findByName(name);
	}

	

}
