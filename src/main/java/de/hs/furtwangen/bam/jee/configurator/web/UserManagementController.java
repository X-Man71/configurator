package de.hs.furtwangen.bam.jee.configurator.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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
import de.hs.furtwangen.bam.jee.configurator.web.domain.Password;
import de.hs.furtwangen.bam.jee.configurator.web.domain.UserEventAdd;
import de.hs.furtwangen.bam.jee.configurator.web.domain.UserEventEdit;

/**
 * 
 * @author Christian Henle
 *
 *         This Controller provides functions to add, edit, delete and disable
 *         users.
 *
 */

@Controller
@RequestMapping(value = "/management/user")
public class UserManagementController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserManagementController.class);

	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUserPage(Model model) {
		logger.info("addUserPage");
		model.addAttribute("pageHeader", "management.user.form.add.pageHeader");
		model.addAttribute("passwordField", true);
		model.addAttribute("user",
				userManagementService.getNewUserWithAllRoles());
		model.addAttribute("action", "add");

		return "/management/user/form";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") UserEventAdd user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {
		logger.info("addUser");
		model.addAttribute("pageHeader", "management.user.form.add.pageHeader");
		model.addAttribute("passwordField", true);

		user.setAllRoles(userManagementService.findAllRole());
		model.addAttribute("user", user);

		if (!user.getPassword().passwordEquals()) {
			model.addAttribute("passwordError",
					"management.user.form.add.error.pasword.equals");
			// Alle Rollen für Form setzen
			return "/management/user/form";
		}
		if (bindingResult.hasErrors()) {
			// Problem with username Variable ex: to Long
			// Problem with password Variable ex: to Long, to Short, not Equals
			return "/management/user/form";
		}

		// No Role Selected
		if (null == user.getRolesChecked()) {
			model.addAttribute("roleError",
					"management.user.form.add.error.role.notSelected");
			return "/management/user/form";
		}

		// Username not unique
		try {
			userManagementService.addUser(user);
		} catch (DuplicateUserException e) {
			model.addAttribute("usernameError",
					"management.user.form.add.error.username.unique");
			return "/management/user/form";
		}

		// Succesfully saved
		model.addAttribute("user",
				userManagementService.getNewUserWithAllRoles());
		model.addAttribute("changeSuccessful",
				"management.user.form.add.changeSuccessful");
		return "/management/user/form";
	}

	@RequestMapping(value = "/table", method = RequestMethod.GET)
	public String tableUser(Model model) {
		model.addAttribute("pageHeader", "management.user.table.pageHeader");
		model.addAttribute("users", userManagementService.findAllUser());

		return "/management/user/table";
	}

	@RequestMapping(value = "/table/edit", method = RequestMethod.GET)
	public String editUserTable(Model model) {
		model.addAttribute("pageHeader", "management.user.edit.pageHeader");
		model.addAttribute("users", userManagementService.findAllUser());
		model.addAttribute("edit", true);

		return "/management/user/table";
	}

	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
	public String editUserPage(@PathVariable Long userId, Model model) {
		model.addAttribute("pageHeader", "management.user.edit.pageHeader");
		model.addAttribute("user", userManagementService.findUserbyId(userId));
		model.addAttribute("passwordField", false);
		model.addAttribute("action", "/management/user/edit/" + userId);

		return "/management/user/form";
	}

	@RequestMapping(value = "/edit/{userId}", method = RequestMethod.POST)
	public String editUser(@PathVariable Long userId,
			@Valid @ModelAttribute("user") UserEventEdit user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {

		model.addAttribute("pageHeader", "management.user.edit.pageHeader");
		model.addAttribute("passwordField", false);
		model.addAttribute("action", "/management/user/edit/" + userId);

		user.setAllRoles(userManagementService.findAllRole());
		model.addAttribute("user", user);

		if (bindingResult.hasErrors()) {
			// Problem with username Variable ex: to Long
			// Problem with password Variable ex: to Long, to Short, not Equals
			return "/management/user/form";
		}

		// No Role Selected
		if (null == user.getRolesChecked()) {
			model.addAttribute("roleError",
					"management.user.edit.error.role.notSelected");
			return "/management/user/form";
		}

		try {
			userManagementService.updateUser(userId, user);
			redirectAttributes.addFlashAttribute("changeSuccessful",
					"management.user.edit.changeSuccessful");

			return "redirect:/management/user/table/edit";
			// Username not unique
		} catch (DuplicateUserException e) {
			model.addAttribute("usernameError",
					"management.user.edit.error.username.unique");

			return "/management/user/form";
		} catch (ObjectOptimisticLockingFailureException ol) {
			model.addAttribute("formError",
					"management.user.edit.error.optimisticLocking");
		}

		return "/management/user/form";
	}

	@RequestMapping(value = "/table/enable", method = RequestMethod.GET)
	public String enableUserTable(Model model) {
		model.addAttribute("pageHeader",
				"management.user.table.enable.pageHeader");
		model.addAttribute("users", userManagementService.findAllUser());
		model.addAttribute("enable", true);

		return "/management/user/table";
	}

	@RequestMapping(value = "/enable/{userId}", method = RequestMethod.GET)
	public String enableUserPage(@PathVariable Long userId, Model model) {

		model.addAttribute("user", userManagementService.findUserbyId(userId));
		model.addAttribute("action", "/management/user/enable/" + userId);
		boolean[] array = new boolean[2];
		array[0] = true;
		array[1] = false;
		model.addAttribute("enabledOptions", array);

		return "/management/user/enable";
	}

	@RequestMapping(value = "/enable/{userId}", method = RequestMethod.POST)
	public String enableUser(@PathVariable Long userId,
			@Valid @ModelAttribute("user") UserEventEdit user,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {

		try {
			userManagementService.updateUserStatus(userId, user);

		} catch (ObjectOptimisticLockingFailureException ol) {
			boolean[] array = new boolean[2];
			array[0] = true;
			array[1] = false;
			model.addAttribute("enabledOptions", array);
			model.addAttribute("action", "/management/user/enable/" + userId);
			model.addAttribute("formError",
					"management.user.enable.optimisticLocking");
			model.addAttribute("user",
					userManagementService.findUserbyId(userId));
			return "/management/user/enable";
		}
		model.addAttribute("users", userManagementService.findAllUser());
		model.addAttribute("enable", true);

		redirectAttributes.addFlashAttribute("changeSuccessful",
				"management.user.enable.changeSuccessful");

		return "redirect:/management/user/table/enable";
	}

	@RequestMapping(value = "/table/password", method = RequestMethod.GET)
	public String changePasswordUserTable(Model model) {
		model.addAttribute("pageHeader", "management.user.password.pageHeader");
		model.addAttribute("users", userManagementService.findAllUser());
		model.addAttribute("password", true);

		return "/management/user/table";
	}

	@RequestMapping(value = "/password/{userId}", method = RequestMethod.GET)
	public String changePassowrdUserPage(@PathVariable Long userId, Model model) {

		Password password = new Password();
		password.setVersion(userManagementService.findUserbyId(userId)
				.getVersion());

		model.addAttribute("pageHeader", "management.user.password.pageHeader");
		model.addAttribute("password", password);
		model.addAttribute("action", "/management/user/password/" + userId);

		return "/management/user/password";
	}

	@RequestMapping(value = "/password/{userId}", method = RequestMethod.POST)
	public String changePasswordUser(@PathVariable Long userId,
			@Valid @ModelAttribute("password") Password password,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			Model model) {

		model.addAttribute("pageHeader", "management.user.password.pageHeader");
		model.addAttribute("action", "/management/user/password/" + userId);

		if (bindingResult.hasErrors()) {
			// Problem with username Variable ex: to Long
			// Problem with password Variable ex: to Long, to Short, not Equals
			return "/management/user/password";
		}

		if (!password.passwordEquals()) {
			model.addAttribute("passwordError",
					"management.user.form.add.error.pasword.equals");
			return "/management/user/password";
		}
		try {
			userManagementService.updateUserPassword(userId, password);
		} catch (ObjectOptimisticLockingFailureException ol) {
			model.addAttribute("formError",
					"management.user.password.optimisticLocking");
			return "/management/user/password";
		}

		redirectAttributes.addFlashAttribute("changeSuccessful",
				"management.user.password.changeSuccessful");

		return "redirect:/management/user/table/password";
	}

}
