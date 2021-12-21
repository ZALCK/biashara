package com.elgroup.biashara.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elgroup.biashara.user.partner.Partner;

public interface IProductDAO extends JpaRepository<Product, Long> {
	public List<Product> findByPartner(Partner partner);
}
