package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.hs.furtwangen.bam.jee.configurator.model.ProductType;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long>, PagingAndSortingRepository<ProductType, Long>, ProductTypeRepositoryCustom {

}
