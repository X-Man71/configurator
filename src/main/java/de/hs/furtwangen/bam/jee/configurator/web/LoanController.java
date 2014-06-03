package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.model.Loan;

public class LoanController {

	@RequestMapping(value = "/loan/new", method = RequestMethod.POST)
	public String sendLoanRequest(@ModelAttribute("loan") Loan loan,
			Model model, BindingResult result) {

		// send loan object
		
		return "/customer/request";
	}
	
}
