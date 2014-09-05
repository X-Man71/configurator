package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.service.StartPageService;

@Controller
public class StartPageController {
	
	@Autowired
	private StartPageService startPageService; 
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String startPage(Model model) {
		
		model.addAttribute("tableCustomers",startPageService.findAllTableCustomer());

		return "home";
	}

}
