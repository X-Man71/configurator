package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {
	
	@RequestMapping(value = "/customer/checkout", method = RequestMethod.GET)
	public String customerCheckout(Model model) {

		
		return "/customer/checkout";
	}

}
