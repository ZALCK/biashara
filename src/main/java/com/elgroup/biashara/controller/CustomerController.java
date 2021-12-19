package com.elgroup.biashara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elgroup.biashara.user.customer.Customer;
import com.elgroup.biashara.user.customer.ICustomerService;

@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private ICustomerService ics;

	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAdd(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "/customer/add";
	}

	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String add(@ModelAttribute(value="customer") Customer customer) {
		ics.create(customer);
		
		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Customer customer = ics.getCustomer(id);
		model.addAttribute("customer", customer);
		return "/customer/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("customer") Customer customer) {
		Customer mergeCustomerInfos = ics.getCustomer(customer.getId());
		mergeCustomerInfos.setEmail(customer.getEmail());
		mergeCustomerInfos.setFirstname(customer.getFirstname());
		mergeCustomerInfos.setLastname(customer.getLastname());
		mergeCustomerInfos.setPhone(customer.getPhone());
		mergeCustomerInfos.setCity(customer.getCity());
		ics.update(mergeCustomerInfos);

		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model){
		Customer customer = ics.getCustomer(id);
		ics.delete(customer);
		
		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public String reset(@PathVariable("id") long id, Model model){
		Customer customer = ics.getCustomer(id);
		customer.setPassword(customer.getEmail());
		customer.setEnabled(false);
		ics.update(customer);
		
		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
	public String enabledOrDisable(@PathVariable("id") long id, Model model){
		Customer customer = ics.getCustomer(id);
		customer.setEnabled(!customer.isEnabled());
		ics.update(customer);
		
		return "redirect:/customer/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		List<Customer> liste = ics.getAll();
		model.addAttribute("customers", liste);
		
		return "/customer/list";
	}
}
