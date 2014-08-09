package de.hs.furtwangen.bam.jee.configurator.service;

import de.hs.furtwangen.bam.jee.configurator.model.Authority;

public interface IAuthorityService {

	public abstract Authority findAuthority(String name);

}