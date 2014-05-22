package de.hs.furtwangen.bam.jee.configurator.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FlowController {	
		
		@RequestMapping(value = "/flow", method = RequestMethod.GET)
		public String admin(Model model) {

			
			return "/flow";
		}
		
		@RequestMapping(value = "/startFlow", method = RequestMethod.GET)
		public String startFlow(Model model) {

			
			return "/startFlow";
		}




}
