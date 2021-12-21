package com.elgroup.biashara.product;

import java.util.List;

import com.elgroup.biashara.user.partner.Partner;

public interface IProductService {
	public void create (Product product);
	public void update (Product product);
	public Product getProduct (Long id);
	public void delete (Product product);
	public List<Product> getAll();
	
	public List<Product> findByPartner(Partner partner);
	
}
