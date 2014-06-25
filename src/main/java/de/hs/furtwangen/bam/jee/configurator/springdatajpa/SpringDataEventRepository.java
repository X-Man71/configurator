package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import de.hs.furtwangen.bam.jee.configurator.model.Event;

public interface SpringDataEventRepository extends CrudRepository<Event, Long> {

	public Event findByName(String name);
	
	public List<Event> findAll();
	
	//TODO asdf
//	@Query("SELECT e FROM Event e WHERE LOWER(e.user) = LOWER(:lastName)")
//    public List<Event> findEventsByUser(@Param("username") String username);

}
