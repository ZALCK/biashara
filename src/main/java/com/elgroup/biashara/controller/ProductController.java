package com.elgroup.biashara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elgroup.biashara.product.IProductService;
import com.elgroup.biashara.product.Product;
import com.elgroup.biashara.user.User;
import com.elgroup.biashara.user.partner.Partner;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	@Autowired
	private IProductService ips;
	@Autowired
	MainController mc;
	
	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAdd(Model model) {
		Product product = new Product();
		product.setInStock(true);
		model.addAttribute("product", product);
		
		return "/product/add";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String add(@ModelAttribute(value="product") Product product) {
		User userConnected = mc.getCurrentUserConnected();
		product.setPartner((Partner)userConnected);
		ips.create(product);
		
		return "redirect:/product/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Product product = ips.getProduct(id);
		model.addAttribute("product", product);
		
		return "/product/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("product") Product product) {
		Product mergeProductInfos = ips.getProduct(product.getId());
		mergeProductInfos.setName(product.getName());
		mergeProductInfos.setPrice(product.getPrice());
		mergeProductInfos.setDescription(product.getDescription());
		mergeProductInfos.setInStock(product.isInStock());
		mergeProductInfos.setImageLink(product.getImageLink());
		ips.update(mergeProductInfos);
		
		return "redirect:/product/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model){
		Product product = ips.getProduct(id);
		ips.delete(product);
		return "redirect:/product/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		User userConnected = mc.getCurrentUserConnected();
		List<Product> liste = ips.findByPartner((Partner)userConnected);
		model.addAttribute("products", liste);
		return "/product/list";
	}
	
}
