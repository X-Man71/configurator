package de.hs.furtwangen.bam.jee.configurator.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StartController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String intro(Model model) {
		return "home";
	}

	@RequestMapping
	public String noPageAvailable(final HttpServletRequest request) {
		return "home";

	}

}
