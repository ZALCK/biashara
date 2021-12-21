package com.elgroup.biashara.industry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndustryServiceImpl implements IIndustryService{

	@Autowired
	IIndustryDAO ird;
	
	@Override
	public void create(Industry industry) {
		// TODO Auto-generated method stub
		ird.save(industry);
	}

	@Override
	public void update(Industry industry) {
		// TODO Auto-generated method stub
		ird.saveAndFlush(industry);
	}

	@Override
	public Industry getIndustry(Long id) {
		// TODO Auto-generated method stub
		return ird.getById(id);
	}

	@Override
	public void delete(Industry industry) {
		// TODO Auto-generated method stub
		ird.delete(industry);
	}

	@Override
	public List<Industry> getAll() {
		// TODO Auto-generated method stub
		return ird.findAll();
	}

	@Override
	public Industry getIndustryByName(String name) {
		// TODO Auto-generated method stub
		return ird.findByName(name);
	}

}
