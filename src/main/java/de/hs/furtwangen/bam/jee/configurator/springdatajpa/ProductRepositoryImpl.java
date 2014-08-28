package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import de.hs.furtwangen.bam.jee.configurator.model.Product;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	private QueryDslJpaRepository<Product, Long> productRepository;
	
	

	@PostConstruct
	public void init() {
		JpaEntityInformation<Product, Long> productEntityInfo = new JpaMetamodelEntityInformation<Product, Long>(
				Product.class, entityManager.getMetamodel());
		productRepository = new QueryDslJpaRepository<Product, Long>(productEntityInfo,
				entityManager);
	}

}
