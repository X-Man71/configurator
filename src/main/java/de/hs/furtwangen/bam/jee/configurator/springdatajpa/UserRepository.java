package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.hs.furtwangen.bam.jee.configurator.model.User;


public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long>, UserRepositoryCustom {
	
	public User findByUsername(String username);
	
	public List<User> findAll();
	
}
