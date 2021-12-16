package com.elgroup.biashara.security;

import java.util.List;

public interface IPrivilegeService {
	public void create (Privilege privilege);
	public void update (Privilege privilege);
	public Privilege getPrivilege (Long id);
	public void delete (Privilege privilege);
	public List<Privilege> getAll();
}
