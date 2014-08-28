package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import de.hs.furtwangen.bam.jee.configurator.model.ProductType;

public class ProductTypeRepositoryImpl implements ProductRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;

	private QueryDslJpaRepository<ProductType, Long> productTypeRepository;
	
	

	@PostConstruct
	public void init() {
		JpaEntityInformation<ProductType, Long> productTypeEntityInfo = new JpaMetamodelEntityInformation<ProductType, Long>(
				ProductType.class, entityManager.getMetamodel());
		productTypeRepository = new QueryDslJpaRepository<ProductType, Long>(productTypeEntityInfo,
				entityManager);
	}

}
