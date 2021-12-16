package com.elgroup.biashara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elgroup.biashara.user.partner.Partner;
import com.elgroup.biashara.user.partner.IPartnerService;

@Controller
@RequestMapping(value = "/partner")
public class PartnerController {

	@Autowired
	private IPartnerService ips;
	
	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAdd(Model model) {
		Partner partner = new Partner();
		model.addAttribute("partner", partner);
		
		return "/partner/add";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String add(@ModelAttribute(value="partner") Partner partner) {
		ips.create(partner);
		
		return "redirect:/partner/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Partner partner = ips.getPartner(id);
		model.addAttribute("partner", partner);
		
		return "/partner/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("partner") Partner partner) {
		Partner mergePartnerInfos = ips.getPartner(partner.getId());
		mergePartnerInfos.setEmail(partner.getEmail());
		mergePartnerInfos.setFirstname(partner.getFirstname());
		mergePartnerInfos.setLastname(partner.getLastname());
		mergePartnerInfos.setPhone(partner.getPhone());
		mergePartnerInfos.setIdCard(partner.getIdCard());
		mergePartnerInfos.setBusinessLogo(partner.getBusinessLogo());
		mergePartnerInfos.setBusinessName(partner.getBusinessName());
		mergePartnerInfos.setBusinessSlogan(partner.getBusinessSlogan());
		ips.update(mergePartnerInfos);
		
		return "redirect:/partner/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model){
		Partner partner = ips.getPartner(id);
		ips.delete(partner);
		
		return "redirect:/partner/list";
	}
	
	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public String reset(@PathVariable("id") long id, Model model){
		Partner partner = ips.getPartner(id);
		partner.setPassword(partner.getEmail());
		partner.setEnabled(false);
		ips.update(partner);
		
		return "redirect:/partner/list";
	}
	
	@RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
	public String enabledOrDisable(@PathVariable("id") long id, Model model){
		Partner partner = ips.getPartner(id);
		partner.setEnabled(!partner.isEnabled());
		ips.update(partner);
		
		return "redirect:/partner/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		List<Partner> liste = ips.getAll();
		model.addAttribute("partners", liste);
		
		return "/partner/list";
	}
}
