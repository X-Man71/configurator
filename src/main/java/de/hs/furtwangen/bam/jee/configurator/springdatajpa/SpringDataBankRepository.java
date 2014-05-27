package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Bank;
import de.hs.furtwangen.bam.jee.configurator.model.Loan;


public interface SpringDataBankRepository extends CrudRepository<Bank, Long> {
	
	public Loan findByName(String name);
	
}
