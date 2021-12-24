package com.elgroup.biashara.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.elgroup.biashara.command.Command;
import com.elgroup.biashara.command.CommandLine;
import com.elgroup.biashara.command.ICommandLineService;
import com.elgroup.biashara.command.ICommandService;
import com.elgroup.biashara.product.IProductService;
import com.elgroup.biashara.user.User;
import com.elgroup.biashara.user.customer.Customer;
import com.elgroup.biashara.user.customer.CustomerServiceImpl;
import com.elgroup.biashara.user.customer.ICustomerService;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired
	IProductService ips;
	@Autowired
	ICustomerService ics;
	@Autowired
	ICommandService icommandeService;
	@Autowired
	ICommandLineService icls;
	@Autowired
	MainController mc;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {		
		return "/cart/index";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "buy/{id}", method = RequestMethod.GET)
	public String buy(@PathVariable("id") long id, HttpSession session) {
		CommandLine commandLine = new CommandLine();
		if (session.getAttribute("cart") == null) {
			List<CommandLine> cart = new ArrayList<CommandLine>();
			commandLine.setProduct(ips.getProduct(id));
			commandLine.setQuantity(1);
			cart.add(commandLine);
			session.setAttribute("cart", cart);
		} else {
			List<CommandLine> cart = (List<CommandLine>) session.getAttribute("cart");
			int index = exists(id, cart);
			if (index == -1) {
			 commandLine.setProduct(ips.getProduct(id));
			 commandLine.setQuantity(1);
			 cart.add(commandLine);
			} else {
				 int quantity = cart.get(index).getQuantity()+ 1;
				 cart.get(index).setQuantity(quantity);
			}
			session.setAttribute("cart", cart);
		}
		return "/cart/index";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable("id") long id, HttpSession session) {
		List<CommandLine> cart = (List<CommandLine>) session.getAttribute("cart");
		int index = exists(id, cart);
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/cart";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/purchase", method = RequestMethod.GET)
	public String purchase(Model model, HttpSession session) {
		User userConnected = mc.getCurrentUserConnected();
		if (userConnected != null) {
			if(userConnected.getRole().getName().equalsIgnoreCase(CustomerServiceImpl.CUSTOMER_ROLE_IN_DATABASE) ) {
				Customer customerConnected = (Customer) userConnected;
				Command command = new Command();
				command.setCustomer(customerConnected);
				List<CommandLine> commandLinesList = (List<CommandLine>) session.getAttribute("cart");
				Set<CommandLine> commandLinesSet = new HashSet<CommandLine>(commandLinesList);
				command.setCommandLines(commandLinesSet);
				model.addAttribute("command", icommandeService.create(command));
				return "cart/command";
			} else {
				model.addAttribute("error", "Vous devez vous connecter avec un compte client pour passer une commande");
				return "cart/index";
			}
		}
		return "redirect:/cart/shipping";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/shipping", method = RequestMethod.GET)
	public String shippingForm(Model model, HttpSession session) {
		if (session.getAttribute("cart") != null) {
			List<CommandLine> commandLines = (List<CommandLine>) session.getAttribute("cart");
			if(!commandLines.isEmpty()) {
				model.addAttribute("customer", new Customer());
				return "/cart/shipping";
			}
		}
		return "redirect:/cart";
	}
	
	private int exists(long id, List<CommandLine> cart) {
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getProduct().getId() == id) {
				return i;
			}
		}
		return -1;
	}
}
