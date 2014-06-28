package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hs.furtwangen.bam.jee.configurator.model.Bank;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.SpringDataBankRepository;

@Service
public class BankService {
	
	@Autowired
	private SpringDataBankRepository springDataBankRepository;
	
	
	public Iterable<Bank> findAllBanks(){
		return springDataBankRepository.findAll();
	}
	
	public void deleteBank(Long id){
		springDataBankRepository.delete(id);
	}
	
	public void saveBank(Bank bank){
		springDataBankRepository.save(bank);
	}

}
