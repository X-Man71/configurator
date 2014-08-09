package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataUserRepository;

@Service
public class AdminService implements IAdminService {
	
	@Autowired
	private SpringDataUserRepository springDataUserRepository;

	@Override
	@Transactional
	public List<User> findAll() {
		return springDataUserRepository.findAll();
	}

}
