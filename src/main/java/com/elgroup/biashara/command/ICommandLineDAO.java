package com.elgroup.biashara.command;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommandLineDAO extends JpaRepository<CommandLine, Long> {
	
}
