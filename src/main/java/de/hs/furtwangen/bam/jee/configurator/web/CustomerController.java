package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.model.LoanRequest;
import de.hs.furtwangen.bam.jee.configurator.service.RequestService;
import de.hs.furtwangen.bam.jee.configurator.service.UserService;

@Controller
public class CustomerController {
	
	@Autowired
	private UserService userService;
	
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
		LoanRequest loanRequest = new LoanRequest();		
		Long userId = userService.getUserIdOfCurrentUser();		
		loanRequest.setUserId(userId);
		
		model.addAttribute("loanRequest", loanRequest);
		return "/customer/request";
	}
	
	@RequestMapping(value = "/customer/request", method = RequestMethod.POST)
	public String requestPost(Model model, LoanRequest loanRequest) {
		
		System.out.println(loanRequest.toString());
		
		new RequestService().sendRequest(loanRequest);
		return "redirect:/customer/loan";
	}
	
}
