package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.Event;

public interface IEventService {

	public abstract int getNumberOfEventsByUser();

	public abstract void save(Event event, String username);

	public abstract void find(String name);

	public abstract List<Event> findAll();

}