package de.hs.furtwangen.bam.jee.configurator.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	private UserEvent getNewUserWithAllRoles() {
		UserEvent user = new UserEvent();
		List<Role> allRoleList = new ArrayList<Role>();
		for (Role role : userManagementService.findAll()) {
			allRoleList.add(role);
		}
		user.setAllRoles(allRoleList);

		List<Long> noRolesChecked = new ArrayList<Long>();
		user.setRolesChecked(noRolesChecked);
		return user;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUserForm(Model model) {

		model.addAttribute("newUserWithRolles", getNewUserWithAllRoles());
		model.addAttribute("action", "add");

		return "/userManagement/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUserFormSave(@Valid @ModelAttribute UserEvent user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {

		/*
		 * for(Integer role : user.getRolesChecked()) {
		 * System.out.println("Role "+role); }
		 */

		List<Role> allRoleList = new ArrayList<Role>();

		for (Role role : userManagementService.findAll()) {
			allRoleList.add(role);
		}
		user.setAllRoles(allRoleList);

		userManagementService.saveUser(user);

		model.addAttribute("newUserWithRolles", getNewUserWithAllRoles());
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
