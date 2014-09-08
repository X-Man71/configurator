package de.hs.furtwangen.bam.jee.configurator.springdatajpa;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;

import com.mysema.query.types.expr.BooleanExpression;

import de.hs.furtwangen.bam.jee.configurator.model.OrderPosition;
import de.hs.furtwangen.bam.jee.configurator.model.Product;
import de.hs.furtwangen.bam.jee.configurator.model.TableCustomer;
import de.hs.furtwangen.bam.jee.configurator.queries.OrderPositionPredicates;

public class OrderPositionRepositoryImpl implements OrderPositionRepositoryCustom {
		
	@PersistenceContext
	private EntityManager entityManager;

	private QueryDslJpaRepository<OrderPosition, Long> orderPositionRepository;
	
	@Override
	public void deleteByTableCustomerAndProductAndRegisteredFalse(TableCustomer tableCustomer, Product product){
		BooleanExpression orderPositionByTableCustomer = OrderPositionPredicates.orderPositionByTableCustomer(tableCustomer);
		BooleanExpression orderPositionByProduct = OrderPositionPredicates.orderPositionByProduct(product);
		BooleanExpression orderPositionByRegistedFalse = OrderPositionPredicates.orderPositionByRegistedFalse();
		
		OrderPosition orderPosition =orderPositionRepository.findAll(orderPositionByTableCustomer.and(orderPositionByProduct.and(orderPositionByRegistedFalse))).get(0);
		orderPositionRepository.delete(orderPosition);
	}
	
	
	
	@PostConstruct
	public void init() {
		JpaEntityInformation<OrderPosition, Long> orderPositionEntityInfo = new JpaMetamodelEntityInformation<OrderPosition, Long>(
				OrderPosition.class, entityManager.getMetamodel());
		orderPositionRepository = new QueryDslJpaRepository<OrderPosition, Long>(orderPositionEntityInfo,
				entityManager);
	}

}
