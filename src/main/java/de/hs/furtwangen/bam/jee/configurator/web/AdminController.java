package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.furtwangen.bam.jee.configurator.model.Authority;
import de.hs.furtwangen.bam.jee.configurator.model.Bank;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.service.BankService;

@Controller
public class AdminController {

	@Autowired
	private BankService bankService;

	@RequestMapping(value = "/admin/banks", method = RequestMethod.GET)
	public String banks(Model model) {
		model.addAttribute("banks", bankService.findAllBanks());

		return "admin/banks";
	}

	@RequestMapping(value = "/admin/delete/bank", method = RequestMethod.POST)
	public String deleteBank(Model model, Long id) {

		bankService.deleteBank(id);

		return "redirect:/admin/banks";
	}

	@RequestMapping(value = "/admin/createBank", method = RequestMethod.GET)
	public String deleteValideLoan(Model model) {
		model.addAttribute("bank", new Bank());
		return "/admin/createBank";
	}
	
	@RequestMapping(value = "/admin/createBank", method = RequestMethod.POST)
	public String saveRegisteredUser(@ModelAttribute("bank") Bank bank,
			Model model, BindingResult result) {
		
		bankService.saveBank(bank);
		
		return "/admin/createBank";
	}
}
