package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataLoanRepository;

@Service
public class BankService {
	
	@Autowired
	private SpringDataLoanRepository springDataLoanRepository;

}
