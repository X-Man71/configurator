package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hs.furtwangen.bam.jee.configurator.model.Authority;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataAuthoritiesRepository;

@Service
public class AuthorityService {
	
	@Autowired
	private SpringDataAuthoritiesRepository springDataAuthoritiesRepository;
	
	public Authority findAuthority(String name)
	{
		return springDataAuthoritiesRepository.findAuthorityByName(name);
	}

}
