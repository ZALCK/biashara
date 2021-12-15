package com.elgroup.biashara.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDAO extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}
