package com.elgroup.biashara.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	IRoleDAO ird;
	
	@Override
	public void create(Role role) {
		// TODO Auto-generated method stub
		ird.save(role);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		ird.saveAndFlush(role);
	}

	@Override
	public Role getRole(Long id) {
		// TODO Auto-generated method stub
		return ird.getById(id);
	}

	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		ird.delete(role);
	}

	@Override
	public List<Role> getAll() {
		// TODO Auto-generated method stub
		return ird.findAll();
	}

	@Override
	public Role getRoleByName(String name) {
		// TODO Auto-generated method stub
		return ird.findByName(name);
	}

}
