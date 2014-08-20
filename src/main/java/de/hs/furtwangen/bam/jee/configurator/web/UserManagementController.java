package de.hs.furtwangen.bam.jee.configurator.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.service.UserManagementService;

/**
 * 
 * @author Christian Henle
 *
 *         This Controller provides functions to add, edit, delete and disable
 *         users.
 *
 */

@Controller
@RequestMapping(value = "/userManagement")
public class UserManagementController {

	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String createUserForm(Model model) {

		User user = userManagementService.newUserWithAllRolles();
		model.addAttribute("newUserWithRolles", user);
		//model.addAttribute("RoleAll", RoleAll.ALL);

		return "/userManagement/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String createUserSave(@Valid @ModelAttribute User user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		
		
		return "/userManagement/add";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String updateUserForm() {
		return "";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String updateUserSave() {
		return "";
	}

}
