package com.elgroup.biashara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elgroup.biashara.industry.IIndustryService;
import com.elgroup.biashara.industry.Industry;

@Controller
@RequestMapping(value = "/industry")
public class IndustryController {
	
	@Autowired
	private IIndustryService iis;
	
	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAdd(Model model) {
		Industry industry = new Industry();
		model.addAttribute("industry", industry);
		return "/industry/add";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String add(@ModelAttribute(value="industry") Industry industry) {
		iis.create(industry);
		return "redirect:/industry/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Industry industry = iis.getIndustry(id);
		model.addAttribute("industry", industry);
		return "/industry/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("industry") Industry industry) {
		Industry mergeIndustryInfos = iis.getIndustry(industry.getId());
		mergeIndustryInfos.setName(industry.getName());
		iis.update(mergeIndustryInfos);
		
		return "redirect:/industry/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model){
		Industry industry = iis.getIndustry(id);
		iis.delete(industry);
		return "redirect:/industry/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		List<Industry> liste = iis.getAll();
		model.addAttribute("industries", liste);
		return "/industry/list";
	}
}
