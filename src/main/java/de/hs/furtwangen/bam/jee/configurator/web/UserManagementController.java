package de.hs.furtwangen.bam.jee.configurator.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hs.furtwangen.bam.jee.configurator.Exception.DuplicateUserException;
import de.hs.furtwangen.bam.jee.configurator.service.UserManagementService;
import de.hs.furtwangen.bam.jee.configurator.web.domain.UserEvent;

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
	
	private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUserPage(Model model) {
		logger.info("addUserPage");
		model.addAttribute("pageHeader", "userManagement.form.add.pageHeader");
		model.addAttribute("user",
				userManagementService.getNewUserWithAllRoles());
		model.addAttribute("action", "add");

		return "/userManagement/form";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") UserEvent user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		logger.info("addUser");
		model.addAttribute("pageHeader", "userManagement.form.add.pageHeader");
		
		user.setAllRoles(userManagementService.findAllRole());
		model.addAttribute("user", user);
		
		if(!user.passwordEquals()){
			model.addAttribute("passwordError", "userManagement.form.add.error.pasword.equals");
			//Alle Rollen für Form setzen
			return "/userManagement/form";
		}
		 if (bindingResult.hasErrors()) {
			//Problem with username Variable ex: to Long				
			//Problem with password Variable ex: to Long, to Short, not Equals			 
			return "/userManagement/form";
		 }	
		 
		//No Role Selected
		if(null == user.getRolesChecked())
		{
			model.addAttribute("roleError", "userManagement.form.add.error.role.notSelected");
			return "/userManagement/form";
		}
		
		//Username not unique	
		try {
			userManagementService.saveUser(user);
		} catch (DuplicateUserException e) {
			model.addAttribute("usernameError", "userManagement.form.add.error.username.unique");
			return "/userManagement/form";
		}		

		// Succesfully saved
		model.addAttribute("user",
				userManagementService.getNewUserWithAllRoles());
		return "/userManagement/form";
	}

	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String tableUser(Model model) {
		model.addAttribute("users", userManagementService.findAllUser());
		return "/userManagement/table";
	}

	@RequestMapping(value = "/table/edit", method = RequestMethod.GET)
	public String editUserTable(Model model) {
		model.addAttribute("users", userManagementService.findAllUser());
		model.addAttribute("edit", true);

		return "/userManagement/table";
	}

	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
	public String editUserPage(@PathVariable Long userId, Model model) {
		model.addAttribute("user", userManagementService.findUserbyId(userId));
		model.addAttribute("pageHeader", "Benutzer ändern");

		return "/userManagement/form";
	}

}
