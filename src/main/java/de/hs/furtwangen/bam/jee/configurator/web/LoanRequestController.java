package de.hs.furtwangen.bam.jee.configurator.web;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.model.Loan;
import de.hs.furtwangen.bam.jee.configurator.model.LoanRequest;
import de.hs.furtwangen.bam.jee.configurator.service.UserService;

public class LoanRequestController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/loanRequest/new", method = RequestMethod.POST)
	public String sendLoanRequest(@ModelAttribute("loanRequest") Loan loan,
			Model model, BindingResult result) {

		
		return "/customer/request";
	}
	
	@RequestMapping(value = "/loanRequest/new", method = RequestMethod.GET)
	public String registerNewUser(Model model) {
		

		return "/customer/request";
	}
	
}
