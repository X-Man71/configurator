package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import de.hs.furtwangen.bam.jee.configurator.model.OrderPosition;
import de.hs.furtwangen.bam.jee.configurator.model.TableCustomer;

public interface OrderPositionRepository extends CrudRepository<OrderPosition, Long>,
		PagingAndSortingRepository<OrderPosition, Long> {	
	
	public Iterable<OrderPosition> findByTableCustomerAndDoneFalse(TableCustomer tableCustomer);


}
