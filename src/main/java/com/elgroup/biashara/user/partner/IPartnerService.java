package com.elgroup.biashara.user.partner;

import java.util.List;

import com.elgroup.biashara.exception.UserAlreadyExistException;

public interface IPartnerService {
	public void create (Partner customer);
	public void update (Partner customer);
	public Partner getPartner (Long id);
	public void delete (Partner partner);
	public List<Partner> getAll();
	
	public Partner registerNewPartnerAccount(Partner customer) throws UserAlreadyExistException;
	public Partner findByEmail(String email);
}
