package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.Exception.DuplicateException;
import de.hs.furtwangen.bam.jee.configurator.model.Role;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.RoleRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.UserRepository;
import de.hs.furtwangen.bam.jee.configurator.web.domain.Password;
import de.hs.furtwangen.bam.jee.configurator.web.domain.UserEventAdd;
import de.hs.furtwangen.bam.jee.configurator.web.domain.UserEventEdit;

@Service
@Transactional
public class UserManagementService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<Role> findAllRole() {
		List<Role> allRoleList = new ArrayList<Role>();
		for (Role role : roleRepository.findAll()) {
			allRoleList.add(role);
		}
		return allRoleList;
	}

	@Transactional(readOnly = true)
	public Iterable<User> findAllUser() {
		return userRepository.findAll();
	}

	@Transactional
	public void addUser(UserEventAdd userEvent) throws DuplicateException {
		User user = new User();
		user.setEnabled(true);
		user.setUsername(userEvent.getUsername());
		user.setPassword(new BCryptPasswordEncoder().encode(userEvent
				.getPassword().getPassword1()));
		user.setVersion(userEvent.getVersion());

		Set<User> setUser = new HashSet<User>();
		setUser.add(user);

		List<Role> roleList = new ArrayList<Role>();
		for (Role ro : roleRepository.findAll(userEvent.getRolesChecked())) {
			roleList.add(ro);
		}

		user.setRolesUser(roleList);

		if (null != userRepository.findByUsername(user.getUsername())) {
			throw new DuplicateException();
		}
		userRepository.save(user);
	}

	@Transactional
	public UserEventAdd getNewUserWithAllRoles() {
		UserEventAdd user = new UserEventAdd();
		List<Role> allRoleList = new ArrayList<Role>();
		for (Role role : roleRepository.findAll()) {
			allRoleList.add(role);
		}
		user.setAllRoles(allRoleList);

		List<Long> noRolesChecked = new ArrayList<Long>();
		user.setRolesChecked(noRolesChecked);
		return user;
	}

	@Transactional
	public UserEventEdit findUserbyId(Long userId) {
		User user = userRepository.findOne(userId);

		UserEventEdit userEvent = new UserEventEdit();

		userEvent.setVersion(user.getVersion());
		userEvent.setUsername(user.getUsername());
		userEvent.setEnabled(user.getEnabled());

		List<Role> allRoleList = new ArrayList<Role>();
		for (Role role : roleRepository.findAll()) {
			allRoleList.add(role);
		}
		userEvent.setAllRoles(allRoleList);

		List<Long> rolesChecked = new ArrayList<Long>();
		for (Role r : roleRepository.findAllRoleForUser(user.getId())) {
			rolesChecked.add(r.getId());
		}
		userEvent.setRolesChecked(rolesChecked);

		return userEvent;
	}

	@Transactional
	public void updateUser(Long userId, UserEventEdit userEvent)
			throws DuplicateException {

		if (null != userRepository.findByIdNotAndUsername(userId,
				userEvent.getUsername())) {
			throw new DuplicateException();
		}

		User user = userRepository.findOne(userId);
	
		
		User user1 = new User();
		user1.setId(userId);
		user1.setEnabled(user.getEnabled());
		user1.setPassword(user.getPassword());
		user1.setUsername(userEvent.getUsername());
		user1.setVersion(userEvent.getVersion());
		

		List<Role> roleList = new ArrayList<Role>();
		for (Role ro : roleRepository.findAll(userEvent.getRolesChecked())) {
			roleList.add(ro);
		}
		user1.setRolesUser(roleList);
		
		userRepository.save(user1);
	}

	@Transactional
	public void updateUserStatus(Long userId, UserEventEdit userEventEdit) {
		User user = userRepository.findOne(userId);
		
		User userNew = new User();
		userNew.setId(userId);
		userNew.setVersion(userEventEdit.getVersion());
		userNew.setUsername(user.getUsername());
		userNew.setPassword(user.getPassword());
		userNew.setEnabled(userEventEdit.isEnabled());
		userNew.setRolesUser(user.getRolesUser());
		
		userRepository.save(userNew);
	}

	@Transactional
	public void updateUserPassword(Long userId, Password password) {
		User user = userRepository.findOne(userId);
		
		User userNew = new User();
		userNew.setId(userId);
		userNew.setVersion(password.getVersion());
		userNew.setUsername(user.getUsername());
		userNew.setPassword(new BCryptPasswordEncoder().encode(password.getPassword1()));
		userNew.setEnabled(userNew.isEnabled());
		userNew.setRolesUser(user.getRolesUser());

		userRepository.save(userNew);
	}

}
