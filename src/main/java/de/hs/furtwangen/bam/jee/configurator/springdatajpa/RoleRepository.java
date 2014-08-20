package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>, PagingAndSortingRepository<Role, Long>, RoleRepositoryCustom {

}
