package com.elgroup.biashara.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeServiceImpl implements IPrivilegeService{

	@Autowired
	IPrivilegeDAO ipd;
	
	@Override
	public void create(Privilege privilege) {
		// TODO Auto-generated method stub
		ipd.save(privilege);
	}

	@Override
	public void update(Privilege privilege) {
		// TODO Auto-generated method stub
		ipd.saveAndFlush(privilege);
	}

	@Override
	public Privilege getPrivilege(Long id) {
		// TODO Auto-generated method stub
		return ipd.getById(id);
	}

	@Override
	public void delete(Privilege privilege) {
		// TODO Auto-generated method stub
		ipd.delete(privilege);
	}

	@Override
	public List<Privilege> getAll() {
		// TODO Auto-generated method stub
		return ipd.findAll();
	}

}
