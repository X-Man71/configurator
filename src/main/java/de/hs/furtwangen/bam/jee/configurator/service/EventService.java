package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.Event;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataEventRepository;

@Service
public class EventService {

	@Autowired
	private SpringDataEventRepository springDataEventRepository;
	
	@Transactional
	public void save(Event event) {
		springDataEventRepository.save(event);
	}
	
	@Transactional
	public void find(String name) {
		springDataEventRepository.findByName(name);
	}
	
}

