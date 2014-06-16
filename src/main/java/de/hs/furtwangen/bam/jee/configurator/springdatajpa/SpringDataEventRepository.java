package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Event;

public interface SpringDataEventRepository extends CrudRepository<Event, Long> {

	public Event findByName(String name);

}
