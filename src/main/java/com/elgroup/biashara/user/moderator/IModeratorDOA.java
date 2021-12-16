package com.elgroup.biashara.user.moderator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IModeratorDOA extends JpaRepository<Moderator, Long> {
	
	public Moderator findByEmail(String email);
}
