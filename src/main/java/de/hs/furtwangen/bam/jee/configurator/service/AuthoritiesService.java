package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hs.furtwangen.bam.jee.configurator.model.Authorities;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataAuthoritiesRepository;

@Service
public class AuthoritiesService {
	
	@Autowired
	private SpringDataAuthoritiesRepository springDataAuthoritiesRepository;
	
	public Authorities findAutoritie(String authoritie)
	{
		return springDataAuthoritiesRepository.findAuthoritiesByAuthoritie(authoritie);
	}

}
