package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.User;
import de.hs.furtwangen.bam.jee.configurator.web.domain.Password;

public interface IUserService {

	public abstract void saveCustomer(User user);

	public abstract void deleteUser(User user);

	public abstract void updateUser(User user);

	public abstract void updatePassword(Password password);

	public abstract String oldPasswordByUserName();

}