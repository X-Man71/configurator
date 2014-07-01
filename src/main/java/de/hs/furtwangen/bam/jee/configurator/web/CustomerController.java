package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.service.IEventService;
import de.hs.furtwangen.bam.jee.configurator.service.IUserService;

/**
 * This Controller is responsible for providing all pages which are used by customer.
 * 
 * @author christianhenle
 */
@Controller
public class CustomerController {
	
	@Autowired
	IEventService eventService;
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value = "/customer/events", method = RequestMethod.GET)
	public String customerCheckout(Model model) {
		model.addAttribute("events", eventService.findEventsByUser());
		return "/customer/events";
	}

}
