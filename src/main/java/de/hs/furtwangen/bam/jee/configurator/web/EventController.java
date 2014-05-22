package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EventController {

	

	@RequestMapping(value = "/eventoptions", method = RequestMethod.GET)
	public String admin(Model model) {

		
		return "/eventoptions";
	}
}
