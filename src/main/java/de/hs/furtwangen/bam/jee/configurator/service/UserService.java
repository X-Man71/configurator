package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataUserRepository;
import de.hs.furtwangen.bam.jee.configurator.web.domain.Password;


@Service
@Transactional(readOnly = true)
public class UserService 
{
	@Autowired
	private SpringDataUserRepository springDataUserRepository;
	
	@Transactional
	public void saveUser(User user)
	{
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		springDataUserRepository.save(user);
	}
	
	@Transactional
	public void deleteUser(User user)
	{
		springDataUserRepository.delete(user);
	}
	
	@Transactional
	public void updateUser(User user)
	{
		springDataUserRepository.save(user);
	}
	
	@Transactional
	public void updatePassword(Password password)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String name = auth.getName();
	    User user = springDataUserRepository.findByUsername(name);
	    user.setPassword(hashPassword(password.getNewPassword()));
		springDataUserRepository.save(user);
	}
	
	@Transactional
	public String oldPasswordByUserName()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		return springDataUserRepository.findByUsername(username).getPassword();
	}
	
	public Long getUserIdOfCurrentUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String name = auth.getName();
		return springDataUserRepository.findByUsername(name).getId();
	}
	
	private String hashPassword(String rawPassword){
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(rawPassword);
	}
}
