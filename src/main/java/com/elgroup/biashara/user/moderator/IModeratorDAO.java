package com.elgroup.biashara.user.moderator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IModeratorDAO extends JpaRepository<Moderator, Long> {
	
	public Moderator findByEmail(String email);
}
