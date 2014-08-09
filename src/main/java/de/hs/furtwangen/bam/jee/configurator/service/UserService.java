package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.Authority;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataAuthorityRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataUserRepository;
import de.hs.furtwangen.bam.jee.configurator.web.domain.Password;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {
	@Autowired
	private SpringDataUserRepository springDataUserRepository;

	@Autowired
	private SpringDataAuthorityRepository springDataAuthorityRepository;

	private static final String ROLE_CUSTOMER = "ROLE_CUSTOMER";

	@Override
	@Transactional
	public void saveCustomer(User user) {
		Authority authority = springDataAuthorityRepository
				.findAuthorityByName(ROLE_CUSTOMER);
		user.add(authority);

		loginUserAfterRegister(user, authority);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		springDataUserRepository.save(user);

	}

	@Override
	@Transactional
	public void deleteUser(User user) {
		springDataUserRepository.delete(user);
	}

	
	@Override
	@Transactional
	public void updateUser(User user) {
		springDataUserRepository.save(user);
	}


	@Override
	@Transactional
	public void updatePassword(Password password) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String name = auth.getName();
		User user = springDataUserRepository.findByUsername(name);
		user.setPassword(hashPassword(password.getNewPassword()));
		springDataUserRepository.save(user);
	}


	@Override
	@Transactional
	public String oldPasswordByUserName() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		return springDataUserRepository.findByUsername(username).getPassword();
	}

	private String hashPassword(String rawPassword) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(rawPassword);
	}

	private void loginUserAfterRegister(User user, Authority authority) {
		List<String> roles = new ArrayList<String>();
		roles.add(authority.getName());

		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(user.getUsername(),
						user.getPassword(), CustomUserDetailService
								.getGrantedAuthorities(roles)));

	}
	
}
