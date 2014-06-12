package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.model.Loan;
import de.hs.furtwangen.bam.jee.configurator.service.LoanService;
import de.hs.furtwangen.bam.jee.configurator.service.UserService;

@Controller
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "customer/loan", method = RequestMethod.GET)
	public String registerNewUser(Model model) {

	model.addAttribute("loans", loanService.findLoanByUser());
		
		return "customer/loan";
	}
	

	@RequestMapping(value = "customer/loan", method = RequestMethod.POST)
	public String sendLoanRequest(@ModelAttribute("loanRequest") Loan loan,
			Model model, BindingResult result) {
	

		return "customer/loan";
	}

	

}
