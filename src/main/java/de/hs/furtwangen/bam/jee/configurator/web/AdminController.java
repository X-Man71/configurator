package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.service.EventService;
import de.hs.furtwangen.bam.jee.configurator.service.UserService;

@Controller
public class AdminController {

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/admin/customers", method = RequestMethod.GET)
	public String adminCustomers(Model model) {
		model.addAttribute("users", userService.findAll());

		System.out.println("events size: " + eventService.findAll().size());

		return "/admin/customers";
	}

	@RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
	public String adminOrders(Model model) {
		model.addAttribute("events", eventService.findAll());

		System.out.println("events size: " + eventService.findAll().size());

		return "/admin/orders";
	}

}
