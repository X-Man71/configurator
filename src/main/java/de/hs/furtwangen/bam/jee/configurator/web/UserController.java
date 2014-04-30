package de.hs.furtwangen.bam.jee.configurator.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hs.furtwangen.bam.jee.configurator.model.Authority;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.service.AuthorityService;
import de.hs.furtwangen.bam.jee.configurator.service.UserService;
import de.hs.furtwangen.bam.jee.configurator.web.domain.Password;

@Controller
public class UserController {

	private static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	private static final String USER_SAVED_MESSAGE = "Benutzer angelegt";

	@RequestMapping(value = "/user/new", method = RequestMethod.GET)
	public String registrateNewUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);

		return "/register";
	}

	@RequestMapping(value = "/user/new", method = RequestMethod.POST)
	public String saveRegistratedUser(@ModelAttribute("user") User user,
			Model model, BindingResult result) {
		System.out.println(user.toString());
		
		  Authority authority = authorityService.findAuthority(ROLE_CUSTOMER);
		  user.add(authority);
		  
		  
		  userService.saveUser(user);
		  model.addAttribute("message", USER_SAVED_MESSAGE);
		 

		return "/register";
	}

	@RequestMapping(value = "/user/changePassword", method = RequestMethod.GET)
	public String changePasswordForm(Model model) {
		Password password = new Password();
		model.addAttribute("password", password);
		return "changePassword";
	}

	@RequestMapping(value = "/user/changePassword", method = RequestMethod.POST)
	public String saveChangePasswordForm(@Valid Password password,
			BindingResult result, RedirectAttributes redirectAttributes) {

		if (!password.isPasswordEquals()) {
			result.rejectValue("password1", "password1.missing",
					"Ihre Eingaben sind nicht identisch");
			return "changePassword";
		}
		
		if(!isPasswordEqualsOldPassword(password))
		{
			result.rejectValue("password1", "password1.missing",
					"Ihre Eingabe des bisheringe Passwords ist nicht korrekt");
			return "changePassword";
		}
		 userService.updatePassword(password);
		   redirectAttributes.addFlashAttribute("messagePasswordChanged", "Successfully added the new event");

		return "redirect:/user/changePassword";
	}
	
	private boolean isPasswordEqualsOldPassword(Password password)
	{
		return new BCryptPasswordEncoder().matches(password.getPassword1(), userService.oldPasswordByUserName());		
	}

	
}
