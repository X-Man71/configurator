package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.Event;
import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataEventRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataUserRepository;

@Service
public class EventService implements IEventService {

	@Autowired
	private SpringDataEventRepository springDataEventRepository;

	@Autowired
	private SpringDataUserRepository springDataUserRepository;

	@Override
	@Transactional
	public List<Event> findEventsByUser() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		User user = springDataUserRepository.findByUsername(username);
		return springDataEventRepository.findEventsByUser(user);
	}

	@Override
	@Transactional
	public void save(Event event, String username) {
		System.out.println("Event: " + event.getDate() + " Audio "
				+ event.getAudio().getAudioType());

		System.out.println("username " + username);

		User user = springDataUserRepository.findByUsername(username);
		event.setUser(user);
		user.add(event);

		springDataEventRepository.save(event);
	}

	@Override
	@Transactional
	public void find(String name) {
		springDataEventRepository.findByName(name);
	}

	@Override
	@Transactional
	public List<Event> findAll() {
		return springDataEventRepository.findAll();
	}

}
