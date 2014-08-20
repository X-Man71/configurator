package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import java.util.List;

import de.hs.furtwangen.bam.jee.configurator.model.Role;

public interface RoleRepositoryCustom  {
	
	public List<Role> findAllRoleForUser(Long userId);

}
