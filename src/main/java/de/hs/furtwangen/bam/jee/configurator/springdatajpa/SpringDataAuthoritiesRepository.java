package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Authorities;

public interface SpringDataAuthoritiesRepository extends
		CrudRepository<Authorities, Long> {
	
	public Authorities findAuthoritiesByAuthoritie(String authoritie);
}
