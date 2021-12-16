package com.elgroup.biashara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elgroup.biashara.user.moderator.Moderator;
import com.elgroup.biashara.user.moderator.IModeratorService;

@Controller
@RequestMapping(value = "/moderator")
public class ModeratorController {

	@Autowired
	private IModeratorService ims;
	
	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAdd(Model model) {
		Moderator moderator = new Moderator();
		model.addAttribute("moderator", moderator);
		return "/moderator/add";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String add(@ModelAttribute(value="moderator") Moderator moderator) {
		ims.create(moderator);
		return "redirect:/moderator/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Moderator moderator = ims.getModerator(id);
		model.addAttribute("moderator", moderator);
		return "/moderator/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("moderator") Moderator moderator) {
		Moderator mergeModeratorInfos = ims.getModerator(moderator.getId());
		mergeModeratorInfos.setEmail(moderator.getEmail());
		mergeModeratorInfos.setFirstname(moderator.getFirstname());
		mergeModeratorInfos.setLastname(moderator.getLastname());
		mergeModeratorInfos.setPhone(moderator.getPhone());
		mergeModeratorInfos.setIdCard(moderator.getIdCard());
		ims.update(mergeModeratorInfos);
		
		return "redirect:/moderator/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model){
		Moderator moderator = ims.getModerator(id);
		ims.delete(moderator);
		return "redirect:/moderator/list";
	}
	
	@RequestMapping(value = "/reset/{id}", method = RequestMethod.GET)
	public String reset(@PathVariable("id") long id, Model model){
		Moderator moderator = ims.getModerator(id);
		moderator.setPassword(moderator.getEmail());
		moderator.setEnabled(false);
		ims.update(moderator);
		
		return "redirect:/moderator/list";
	}
	
	@RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
	public String enabledOrDisable(@PathVariable("id") long id, Model model){
		Moderator moderator = ims.getModerator(id);
		moderator.setEnabled(!moderator.isEnabled());
		ims.update(moderator);
		return "redirect:/moderator/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		List<Moderator> liste = ims.getAll();
		model.addAttribute("moderators", liste);
		return "/moderator/list";
	}
}
