package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Event;
import de.hs.furtwangen.bam.jee.configurator.model.User;

public interface SpringDataEventRepository extends CrudRepository<Event, Long> {

	public Event findByName(String name);
	
	public List<Event> findAll();
	
	public List<Event> findEventsByUser(User user);

}
