package com.elgroup.biashara.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.elgroup.biashara.product.Product;
import com.elgroup.biashara.user.IUserService;
import com.elgroup.biashara.user.User;
import com.elgroup.biashara.user.partner.IPartnerService;
import com.elgroup.biashara.user.partner.Partner;

@Controller
public class MainController {
	
	@Autowired
	IUserService ius;
	@Autowired
	IPartnerService ips;
	
	private String CLIENT_ROLE = "CLIENT";
	private String PARTNER_ROLE = "PARTENAIRE";
	private String MODERATOR_ROLE = "MODERATEUR";
	
	@GetMapping(value = "/")
	@ResponseBody
	public String getHomePage(HttpSession session) {
		if (getCurrentUserConnected() != null) {
			User userConnected = getCurrentUserConnected();
			/*if (userConnected.getRole().getPrivileges().contains(ius.getRoleByName(CLIENT_ROLE))) {
				return "redirect:/customer/list";
			} else if (userConnected.getRole().getPrivileges().contains(ius.getRoleByName(PARTNER_ROLE))) {
				return "redirect:/partner/list";
			} else if (userConnected.getRole().getPrivileges().contains(ius.getRoleByName(MODERATOR_ROLE))) {
				return "redirect:/moderator/list";
			} else {*/
				return "Fully authenticated : " + userConnected.toString()
				+ "\n Session ID with httpSession : "+session.getId();
			//}
		}
		return "redirect:/login"
				+ "\n Session ID with httpSession : "+session.getId();
	}
	
	/*
	 * @RequestMapping(value = "/{businessName}", method = RequestMethod.GET) public
	 * String getProductsByBussinessName(@PathVariable("businessName") String
	 * businessName, Model model) { Partner partner =
	 * ips.findByBusinessName(businessName); List<Product> liste =
	 * partner.getProducts(); model.addAttribute("products", liste);
	 * 
	 * return "/product/list"; }
	 */
	
	public User getCurrentUserConnected() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userConnected;
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			userConnected = ius.findByEmail(userDetails.getUsername());
			return userConnected;
		}
		return null;
	}

}
