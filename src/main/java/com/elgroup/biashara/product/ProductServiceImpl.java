package com.elgroup.biashara.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elgroup.biashara.user.partner.Partner;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	IProductDAO ipd;

	@Override
	public void create(Product product) {
		// TODO Auto-generated method stub
		ipd.save(product);
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		ipd.saveAndFlush(product);
	}

	@Override
	public Product getProduct(Long id) {
		// TODO Auto-generated method stub
		return ipd.getById(id);
	}

	@Override
	public void delete(Product product) {
		// TODO Auto-generated method stub
		ipd.delete(product);
	}

	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
		return ipd.findAll();
	}

	@Override
	public List<Product> findByPartner(Partner partner) {
		// TODO Auto-generated method stub
		return ipd.findByPartner(partner);
	}

}
