package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Loan;
import de.hs.furtwangen.bam.jee.configurator.model.User;


public interface SpringDataLoanRepository extends CrudRepository<Loan, String> {
	
	public List<Loan> findLoanByUser(User user);
	

	
}
