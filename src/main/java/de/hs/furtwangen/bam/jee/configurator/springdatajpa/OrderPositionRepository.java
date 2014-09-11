package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.hs.furtwangen.bam.jee.configurator.model.OrderPosition;
import de.hs.furtwangen.bam.jee.configurator.model.Product;
import de.hs.furtwangen.bam.jee.configurator.model.TableCustomer;

public interface OrderPositionRepository extends CrudRepository<OrderPosition, Long>,
		PagingAndSortingRepository<OrderPosition, Long>,OrderPositionRepositoryCustom {	
	
	public Iterable<OrderPosition> findByTableCustomerAndDoneFalse(TableCustomer tableCustomer);
	
	public Iterable<OrderPosition> findByTableCustomerAndRegisteredFalse(TableCustomer tableCustomer);
	
	public Integer countByTableCustomerAndProductAndRegisteredFalse(TableCustomer tableCustomer, Product product);	
	
	public Iterable<OrderPosition> findByRegisteredTrueOrderByIdDesc();
	
	
	/*
	 * 
	 * Called for Websocket update
	 */
	public Iterable<OrderPosition> findByRegisteredTrueAndIdGreaterThanOrderByIdAsc(Long Id);
	
}
