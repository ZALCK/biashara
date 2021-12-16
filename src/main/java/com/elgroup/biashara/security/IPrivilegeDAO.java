package com.elgroup.biashara.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrivilegeDAO extends JpaRepository<Privilege, Long> {
	public Role findByName(String name);
}
