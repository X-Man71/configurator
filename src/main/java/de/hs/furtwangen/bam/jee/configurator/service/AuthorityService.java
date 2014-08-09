package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hs.furtwangen.bam.jee.configurator.model.Authority;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataAuthorityRepository;

@Service
public class AuthorityService implements IAuthorityService {
	
	@Autowired
	private SpringDataAuthorityRepository springDataAuthorityRepository;
	
	@Override
	public Authority findAuthority(String name)
	{
		return springDataAuthorityRepository.findAuthorityByName(name);
	}

}
