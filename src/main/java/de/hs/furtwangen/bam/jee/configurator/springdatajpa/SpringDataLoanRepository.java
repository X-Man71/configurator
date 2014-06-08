package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Loan;


public interface SpringDataLoanRepository extends CrudRepository<Loan, Long> {
	

	
}
