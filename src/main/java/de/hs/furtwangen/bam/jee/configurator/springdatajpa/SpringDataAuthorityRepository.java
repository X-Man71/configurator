package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Authority;

public interface SpringDataAuthorityRepository extends
		CrudRepository<Authority, Long> {
	
	public Authority findAuthorityByName(String name);
	
}
