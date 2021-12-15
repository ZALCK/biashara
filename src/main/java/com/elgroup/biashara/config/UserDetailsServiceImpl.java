package com.elgroup.biashara.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.elgroup.biashara.user.IUserDAO;
import com.elgroup.biashara.user.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
    private IUserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userDAO.findByEmail(username);
        
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new UserDetailsSecurity(user);
	}

}
