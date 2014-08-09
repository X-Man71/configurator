package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.List;

import de.hs.furtwangen.bam.jee.configurator.model.User;

public interface IAdminService {

	public abstract List<User> findAll();

}