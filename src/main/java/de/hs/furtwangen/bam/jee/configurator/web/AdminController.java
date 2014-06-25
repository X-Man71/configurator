package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.model.Event;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.service.IAdminService;
import de.hs.furtwangen.bam.jee.configurator.service.IEventService;
/**
 * This Controller is responsible for providing all pages which are used by admin.
 * 
 * @author christianhenle
 */
@Controller
public class AdminController {

	@Autowired
	private IEventService eventService;

	@Autowired
	private IAdminService adminService;

	/**
	 * Adds all {@link User} to the {@link Model}  for the View
	 * 
	 * @param model
	 * @return html page /admin/customers
	 */
	@RequestMapping(value = "/admin/customers", method = RequestMethod.GET)
	public String adminCustomers(Model model) {
		model.addAttribute("users", adminService.findAll());

		System.out.println("events size: " + eventService.findAll().size());

		return "/admin/customers";
	}
	/**
	 * Adds all {@link Event} to the {@link Model} for the View
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/events", method = RequestMethod.GET)
	public String adminOrders(Model model) {
		model.addAttribute("events", eventService.findAll());

		System.out.println("events size: " + eventService.findAll().size());

		return "/admin/events";
	}

}
