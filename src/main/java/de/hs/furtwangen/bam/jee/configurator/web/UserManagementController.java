package de.hs.furtwangen.bam.jee.configurator.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import de.hs.furtwangen.bam.jee.configurator.model.Role;
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

	@Autowired
	private UserManagementService userManagementService;



	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUserForm(Model model) {

		model.addAttribute("user", userManagementService.getNewUserWithAllRoles());
		model.addAttribute("action", "add");
		model.addAttribute("pageHeader", "Benutzer anlegen");

		return "/userManagement/form";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUserFormSave(@Valid @ModelAttribute UserEvent user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {

		List<Role> allRoleList = new ArrayList<Role>();

		for (Role role : userManagementService.findAllRole()) {
			allRoleList.add(role);
		}
		user.setAllRoles(allRoleList);

		userManagementService.saveUser(user);

		
		//Succesfully saved
		model.addAttribute("newUserWithRolles", userManagementService.getNewUserWithAllRoles());
		return "/userManagement/form";
	}

	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String showUser(Model model) {		
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
	public String editUser(@PathVariable Long userId, Model model) {
		model.addAttribute("user", userManagementService.findUserbyId(userId));
		model.addAttribute("pageHeader", "Benutzer ändern");
		
		return "/userManagement/form";
	}

}
