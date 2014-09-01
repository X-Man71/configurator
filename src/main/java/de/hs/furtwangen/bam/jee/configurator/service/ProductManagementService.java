package de.hs.furtwangen.bam.jee.configurator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hs.furtwangen.bam.jee.configurator.Exception.DuplicateException;
import de.hs.furtwangen.bam.jee.configurator.model.Product;
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
	
	@Transactional(readOnly=true)
	public Iterable<ProductType> findAllProductType(){
		return productTypeRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Page<Product> findAllProduct(){
		//TODO Change PageRequest values
		 return productRepository.findAll(new PageRequest(0, 10));
		
	}
	
	@Transactional
	public void add(Product product) throws DuplicateException
	{
		if(null != productRepository.findByProductnameAndSize(product.getProductname(), product.getSize()))
		{
			throw new DuplicateException();
		}
		
		productRepository.save(product);
	}
	
	

}
