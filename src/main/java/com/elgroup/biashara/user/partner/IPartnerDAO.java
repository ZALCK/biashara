package com.elgroup.biashara.user.partner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IPartnerDAO extends JpaRepository<Partner, Long> {
	
	public Partner findByEmail(String email);
	public Partner findByBusinessName(String businessName);
}
