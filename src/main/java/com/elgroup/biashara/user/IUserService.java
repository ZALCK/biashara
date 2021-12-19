package com.elgroup.biashara.user;

import java.util.List;

import com.elgroup.biashara.exception.UserAlreadyExistException;
import com.elgroup.biashara.security.Role;

public interface IUserService {
	public void create (User user);
	public void update (User user);
	public User getUser (Long id);
	public void delete (User user);
	public List<User> getAll();
	
	public User registerNewUserAccount(User user) throws UserAlreadyExistException;
	public User findByEmail(String email);
	
	// Password resetting methods 
	public void updateResetPasswordToken(String token, String email);
	public User getByResetPasswordToken(String token);
	public void updatePassword(User user, String newPassword);
	
}
