package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Permission;

public interface SpringDataPermissionRepository extends CrudRepository<Permission, Long> {

}
