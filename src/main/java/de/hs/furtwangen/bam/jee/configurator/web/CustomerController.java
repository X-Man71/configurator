package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {
	
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public String admin(Model model) {
		return "/customer/startPage";
	}
	
	@RequestMapping(value = "/customer/creditRequests", method = RequestMethod.GET)
	public String credits(Model model) {
		return "/customer/creditRequests";
	}

	@RequestMapping(value = "/customer/banks", method = RequestMethod.GET)
	public String banks(Model model) {
		return "/customer/banks";
	}
	
	@RequestMapping(value = "/customer/request", method = RequestMethod.GET)
	public String request(Model model) {
		return "/customer/request";
	}
	
}
