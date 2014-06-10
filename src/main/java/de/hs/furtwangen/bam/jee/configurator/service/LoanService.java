package de.hs.furtwangen.bam.jee.configurator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hs.furtwangen.bam.jee.configurator.model.Loan;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataLoanRepository;

@Service
public class LoanService {
	
	@Autowired
	private SpringDataLoanRepository springDataLoanRepository;
	
	@Autowired
	private UserService userService;
	
	
	public Iterable<Loan> findAll(){
		return springDataLoanRepository.findAll();
	}
	
	public List<Loan> findLoanByUser(){
		return springDataLoanRepository.findLoanByUser(userService.getUserCurrentUser());
	}

}
