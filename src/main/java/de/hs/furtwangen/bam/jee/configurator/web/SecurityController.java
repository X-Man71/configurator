package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This Controller is responsible for mapping login Pages
 * 
 * @author christianhenle
 */
@Controller
public class SecurityController {

	/**
	 * Mappes the same Page as Login Method. In addition this Method add
	 * {@link Model} which shows the User that the username and password were
	 * incorrect.
	 * 
	 * @param model
	 * @return html page login
	 * 
	 */	
	@RequestMapping("/login-error")
	public String loginerror(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}
}
