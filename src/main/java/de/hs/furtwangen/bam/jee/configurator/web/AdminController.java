package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

	@RequestMapping(value = "/admin/customers", method = RequestMethod.GET)
	public String adminCustomers(Model model) {
		return "/admin/customers";
	}
	
	
	@RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
	public String adminOrders(Model model) {
		return "/admin/orders";
	}

}
