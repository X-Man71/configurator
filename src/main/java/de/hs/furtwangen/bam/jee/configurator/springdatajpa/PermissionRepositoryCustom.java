package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import java.util.List;

import de.hs.furtwangen.bam.jee.configurator.model.Permission;

public interface PermissionRepositoryCustom {
	
	public List<Permission> findAllPermissionForRole(Long roleId);

}
