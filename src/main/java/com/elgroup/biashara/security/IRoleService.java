package com.elgroup.biashara.security;

import java.util.List;

public interface IRoleService {
	public void create (Role role);
	public void update (Role role);
	public Role getRole (Long id);
	public void delete (Role role);
	public List<Role> getAll();
	
	public Role getRoleByName(String name);
}
