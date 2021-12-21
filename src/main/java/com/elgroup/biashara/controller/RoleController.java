package com.elgroup.biashara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elgroup.biashara.security.IPrivilegeService;
import com.elgroup.biashara.security.IRoleService;
import com.elgroup.biashara.security.Role;

@Controller
@RequestMapping(value = "/role")
public class RoleController {
	
	@Autowired
	private IRoleService irs;
	@Autowired
	private IPrivilegeService ips;
	
	@RequestMapping(value = "/add", method =RequestMethod.GET)
	public String toAdd(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);
		model.addAttribute("allPrivileges", ips.getAll());
		return "/role/add";
	}
	
	@RequestMapping(value = "/add", method=RequestMethod.POST)
	public String add(@ModelAttribute(value="role") Role role) {
		irs.create(role);
		return "redirect:/role/list";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String toUpdate(@PathVariable("id") Long id, Model model) {
		Role role = irs.getRole(id);
		model.addAttribute("role", role);
		model.addAttribute("allPrivileges", ips.getAll());
		return "/role/update";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("role") Role role) {
		Role mergeRoleInfos = irs.getRole(role.getId());
		mergeRoleInfos.setName(role.getName());
		mergeRoleInfos.setPrivileges(role.getPrivileges());
		irs.update(mergeRoleInfos);
		
		return "redirect:/role/list";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String suppression(@PathVariable("id") long id, Model model){
		Role role = irs.getRole(id);
		irs.delete(role);
		return "redirect:/role/list";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(Model model) {
		List<Role> liste = irs.getAll();
		model.addAttribute("roles", liste);
		return "/role/list";
	}
}
