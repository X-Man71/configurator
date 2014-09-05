package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.hs.furtwangen.bam.jee.configurator.model.TableCustomer;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.TableCustomerRepository;
@Service
public class StartPageService {
	
	@Autowired
	private TableCustomerRepository tableCustomerRepository;
	
	public Iterable<TableCustomer> findAllTableCustomer(){
		return tableCustomerRepository.findAll();
	}

}
