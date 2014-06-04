package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.model.LoanRequest;

public class LoanRequestController {

	@RequestMapping(value = "/loanRequest/new", method = RequestMethod.POST)
	public String sendLoanRequest(@ModelAttribute("loanRequest") LoanRequest loanRequest,
			Model model, BindingResult result) {

		// send loan object
		
		return "/customer/request";
	}
	
	@RequestMapping(value = "/loanRequest/new", method = RequestMethod.GET)
	public String registerNewUser(Model model) {
		model.addAttribute("loanRequest", new LoanRequest());

		return "/customer/request";
	}
	
}
