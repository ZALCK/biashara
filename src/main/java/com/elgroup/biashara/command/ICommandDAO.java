package com.elgroup.biashara.command;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommandDAO extends JpaRepository<Command, Long> {
	
}
