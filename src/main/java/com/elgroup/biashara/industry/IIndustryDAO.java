package com.elgroup.biashara.industry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IIndustryDAO extends JpaRepository<Industry, Long> {
	
	public Industry findByName(String name);
}
