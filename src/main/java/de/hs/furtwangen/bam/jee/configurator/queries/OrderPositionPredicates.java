package de.hs.furtwangen.bam.jee.configurator.queries;

import com.mysema.query.types.expr.BooleanExpression;

import de.hs.furtwangen.bam.jee.configurator.model.Product;
import de.hs.furtwangen.bam.jee.configurator.model.QOrderPosition;
import de.hs.furtwangen.bam.jee.configurator.model.TableCustomer;

public class OrderPositionPredicates 
{
	public static BooleanExpression orderPositionByTableCustomer(final TableCustomer tableCustomer)
	{
		QOrderPosition orderPosition = QOrderPosition.orderPosition;
		return orderPosition.tableCustomer.eq(tableCustomer);	
	}
	
	public static BooleanExpression orderPositionByProduct(final Product product){
		QOrderPosition orderPosition = QOrderPosition.orderPosition;
		return orderPosition.product.eq(product);
	}
	
	public static BooleanExpression orderPositionByRegistedFalse(){
		QOrderPosition orderPosition = QOrderPosition.orderPosition;
		return orderPosition.registered.eq(false);
	}
}
