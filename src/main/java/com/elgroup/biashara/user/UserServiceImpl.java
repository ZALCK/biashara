package com.elgroup.biashara.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elgroup.biashara.exception.UserAlreadyExistException;
import com.elgroup.biashara.exception.UserNotFoundException;
import com.elgroup.biashara.security.IRoleDAO;
import com.elgroup.biashara.security.Role;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	IUserDAO iud;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@Override
	public void create(User user) {
		// TODO Auto-generated method stub
		iud.save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		iud.saveAndFlush(user);
	}

	@Override
	public User getUser(Long id) {
		// TODO Auto-generated method stub
		return iud.getById(id);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		iud.delete(user);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return iud.findAll();
	}

	@Override
	public User registerNewUserAccount(User user) throws UserAlreadyExistException {
		// TODO Auto-generated method stub
		if (emailExists(user.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return iud.save(user);
	}
	
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return iud.findByEmail(email);
	}
	
	private boolean emailExists(final String email) {
        return findByEmail(email) != null;
    }
	
	public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        User user = iud.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            iud.saveAndFlush(user);
        } else {
            throw new UserNotFoundException("Could not find any user with the email " + email);
        }
    }
	
	public User getByResetPasswordToken(String token) {
        return iud.findByResetPasswordToken(token);
    }
	
	public void updatePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        iud.saveAndFlush(user);
    }

}
