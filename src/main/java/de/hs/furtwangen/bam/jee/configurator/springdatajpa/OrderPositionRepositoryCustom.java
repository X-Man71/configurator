package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import de.hs.furtwangen.bam.jee.configurator.model.Product;
import de.hs.furtwangen.bam.jee.configurator.model.TableCustomer;

public interface OrderPositionRepositoryCustom {
	
	public void deleteByTableCustomerAndProductAndRegisteredFalse(TableCustomer tableCustomer, Product product);

}
