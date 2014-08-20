package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Permission;

public interface PermissionRepository extends CrudRepository<Permission, Long>, PagingAndSortingRepository<Permission, Long>, PermissionRepositoryCustom {

}
