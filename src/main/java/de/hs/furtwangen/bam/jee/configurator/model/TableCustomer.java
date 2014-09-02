package de.hs.furtwangen.bam.jee.configurator.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tablesCustomer")
public class TableCustomer extends BaseEntity {
	
	private int tableNumber;
	
	@OneToMany(mappedBy="tableCustomer")
	private List<OrderPosition> orderPositionList;

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public List<OrderPosition> getOrderPositionList() {
		return orderPositionList;
	}

	public void setOrderPositionList(List<OrderPosition> orderPositionList) {
		this.orderPositionList = orderPositionList;
	}
	
	
	
	
	

}
