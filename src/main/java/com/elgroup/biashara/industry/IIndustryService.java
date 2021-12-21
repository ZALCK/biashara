package com.elgroup.biashara.industry;

import java.util.List;

public interface IIndustryService {
	public void create (Industry industry);
	public void update (Industry industry);
	public Industry getIndustry (Long id);
	public void delete (Industry industry);
	public List<Industry> getAll();
	
	public Industry getIndustryByName(String name);
}
