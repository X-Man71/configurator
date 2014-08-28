package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.model.ProductType;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.ProductRepository;
import de.hs.furtwangen.bam.jee.configurator.springdatajpa.ProductTypeRepository;

@Service
@Transactional
public class ProductManagementService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Transactional
	public Iterable<ProductType> findAllProductType(){
		return productTypeRepository.findAll();
	}
	
	

}
